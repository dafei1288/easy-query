package com.easy.query.core.proxy.impl;

import com.easy.query.core.expression.builder.AsSelector;
import com.easy.query.core.expression.builder.Selector;
import com.easy.query.core.expression.builder.Setter;
import com.easy.query.core.expression.lambda.SQLExpression1;
import com.easy.query.core.expression.lambda.SQLExpression2;
import com.easy.query.core.expression.lambda.SQLFuncExpression1;
import com.easy.query.core.expression.parser.core.available.TableAvailable;
import com.easy.query.core.proxy.ProxyEntity;
import com.easy.query.core.proxy.SQLColumnSetExpression;
import com.easy.query.core.proxy.SQLSelectAsExpression;
import com.easy.query.core.proxy.core.ColumnSelectSQLContext;
import com.easy.query.core.util.EasyObjectUtil;
import com.easy.query.core.util.EasySQLExpressionUtil;

/**
 * create time 2023/12/27 21:11
 * 文件说明
 *
 * @author xuejiaming
 */
public class SQLColumnIncludeColumn2Impl<TPropertyProxy extends ProxyEntity<TPropertyProxy,TProperty>, TProperty,TProxy extends ProxyEntity<TProxy,TEntity>, TEntity> implements SQLColumnSetExpression {
    private final TableAvailable table;
    private final String selfProperty;
    private final String aliasProperty;
    private final ProxyEntity<TPropertyProxy, TProperty> columnProxy;
    private final TProxy tProxy;
    private final SQLExpression2<TProxy,TPropertyProxy> navigateSelectExpression;

    public  SQLColumnIncludeColumn2Impl(TableAvailable table, String selfProperty, String aliasProperty,ProxyEntity<TPropertyProxy, TProperty> columnProxy,TProxy tProxy, SQLExpression2<TProxy,TPropertyProxy> navigateSelectExpression) {
        this.table = table;
        this.selfProperty = selfProperty;
        this.aliasProperty = aliasProperty;
        this.columnProxy = columnProxy;
        this.tProxy = tProxy;
        this.navigateSelectExpression = navigateSelectExpression;
    }

    @Override
    public void accept(Setter s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void accept(Selector s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void accept(AsSelector s) {

        s.columnInclude(table, selfProperty, aliasProperty, is -> {
            TableAvailable entityTable = is.getEntityQueryExpressionBuilder().getTable(0).getEntityTable();

            if(navigateSelectExpression!=null){
                TPropertyProxy newEntityProxy = columnProxy.create(entityTable, is.getEntityQueryExpressionBuilder(), is.getRuntimeContext());
                TProxy tProxy1 = tProxy.create(null, new ColumnSelectSQLContext());
                navigateSelectExpression.apply(tProxy1,newEntityProxy);
                SQLSelectAsExpression selectAsExpression = tProxy1.getEntitySQLContext().getSelectAsExpression();
                if (selectAsExpression == null) {//全属性映射
                    is.columnAll(entityTable);
                } else {
                    selectAsExpression.accept(is);
                }
            }else{
                is.columnAll(entityTable);
            }
//            SQLSelectAsExpression selectAsExpression = resultProxy.getEntitySQLContext().getSelectAsExpression();
//            if (selectAsExpression == null) {//全属性映射
//            } else {
//                selectAsExpression.accept(is);
//            }
        });


        EasySQLExpressionUtil.appendSelfExtraTargetProperty(s.getEntityQueryExpressionBuilder(), EasyObjectUtil.typeCastNullable(s), table);
    }
}
