package com.easy.query.api.proxy.entity.select;

import com.easy.query.api.proxy.entity.select.extension.EntityAvailable;
import com.easy.query.api.proxy.entity.select.extension.FlatListResultAble;
import com.easy.query.api.proxy.entity.select.extension.queryable.ClientEntityQueryableAvailable;
import com.easy.query.api.proxy.entity.select.extension.queryable.EntityAggregatable1;
import com.easy.query.api.proxy.entity.select.extension.queryable.EntityCountable1;
import com.easy.query.api.proxy.entity.select.extension.queryable.EntityFilterable1;
import com.easy.query.api.proxy.entity.select.extension.queryable.EntitySubQueryConfigurable1;
import com.easy.query.api.proxy.entity.select.extension.queryable.EntitySubQueryToGroupJoinable1;
import com.easy.query.api.proxy.entity.select.extension.queryable.EntityPageAble1;
import com.easy.query.api.proxy.entity.select.extension.queryable.IEntityGroup1;
import com.easy.query.api.proxy.entity.select.extension.queryable.EntityHavingable1;
import com.easy.query.api.proxy.entity.select.extension.queryable.EntityIncludeable1;
import com.easy.query.api.proxy.entity.select.extension.queryable.EntityIncludesable1;
import com.easy.query.api.proxy.entity.select.extension.queryable.EntityJoinable1;
import com.easy.query.api.proxy.entity.select.extension.queryable.EntityOrderable1;
import com.easy.query.api.proxy.entity.select.extension.queryable.EntitySelectable1;
import com.easy.query.api.proxy.entity.select.extension.queryable.IEntityTree1;
import com.easy.query.api.proxy.entity.select.extension.queryable.IEntityUnion1;
import com.easy.query.core.basic.api.internal.ExpressionConfigurable;
import com.easy.query.core.basic.api.internal.QueryStrategy;
import com.easy.query.core.basic.api.select.ClientQueryableAvailable;
import com.easy.query.core.basic.api.select.ClientQueryable;
import com.easy.query.core.basic.api.select.Query;
import com.easy.query.core.expression.lambda.SQLFuncExpression1;
import com.easy.query.core.proxy.ProxyEntity;
import com.easy.query.core.proxy.SQLColumn;
import com.easy.query.core.proxy.SQLSelectAsExpression;

/**
 * create time 2023/12/4 09:59
 * 文件说明
 *
 * @author xuejiaming
 */
public interface EntityQueryable<T1Proxy extends ProxyEntity<T1Proxy, T1>, T1> extends ClientQueryableAvailable<T1>,
        FlatListResultAble<T1Proxy, T1>,
        ExpressionConfigurable<EntityQueryable<T1Proxy, T1>>,
        QueryStrategy<EntityQueryable<T1Proxy, T1>>,
        ClientEntityQueryableAvailable<T1>,
        EntityFilterable1<T1Proxy, T1>,
        EntityCountable1<T1Proxy, T1>,
        EntityHavingable1<T1Proxy, T1>,
        EntityIncludeable1<T1Proxy, T1>,
        EntityIncludesable1<T1Proxy, T1>,
        EntityAggregatable1<T1Proxy, T1>,
        EntityOrderable1<T1Proxy, T1>,
        EntitySelectable1<T1Proxy, T1>,
        IEntityUnion1<T1Proxy, T1>,
        IEntityGroup1<T1Proxy, T1>,
        EntityJoinable1<T1Proxy, T1>,
        EntitySubQueryToGroupJoinable1<T1Proxy, T1>,
        EntitySubQueryConfigurable1<T1Proxy, T1>,
//        EntityFillable1<T1Proxy,T1>,
//        EntityMultiable1<T1Proxy,T1>,
        IEntityTree1<T1Proxy, T1>,
        EntityAvailable<T1Proxy, T1>,
        EntityPageAble1<T1Proxy, T1> {


    ClientQueryable<T1> getClientQueryable();
//
//    @Deprecated
//    <TRProxy extends ProxyEntity<TRProxy, TR>, TR> List<TR> toList(TRProxy trProxy);

    /**
     * 克隆一个查询表达式为了能够复用给其他代码而不再需要重新创建一个查询对象
     * @return
     */
    @Override
    EntityQueryable<T1Proxy, T1> cloneQueryable();


    @Override
    default EntityQueryable<T1Proxy, T1> toCteAs(){
//        getClientQueryable().getRuntimeContext()
        return toCteAs(getRuntimeContext().getCteTableNamedProvider().getDefaultCteTableName(queryClass()));
    }

    @Override
    EntityQueryable<T1Proxy, T1> toCteAs(String tableName);

    /**
     * 设置column所有join表都会生效
     *
     * @param columns
     * @return
     */
    @Override
    EntityQueryable<T1Proxy, T1> select(String columns);

    default EntityQueryable<T1Proxy, T1> distinct() {
        return distinct(true);
    }

    EntityQueryable<T1Proxy, T1> distinct(boolean condition);

    @Override
    default EntityQueryable<T1Proxy, T1> limit(long rows) {
        return limit(true, rows);
    }

    @Override
    default EntityQueryable<T1Proxy, T1> limit(boolean condition, long rows) {
        return limit(condition, 0, rows);
    }

    @Override
    default EntityQueryable<T1Proxy, T1> limit(long offset, long rows) {
        return limit(true, offset, rows);
    }

    @Override
    EntityQueryable<T1Proxy, T1> limit(boolean condition, long offset, long rows);

    /**
     * 先limit再select
     * @param offset
     * @param rows
     * @param selectExpression
     * @return
     * @param <TRProxy>
     * @param <TR>
     */
    default <TRProxy extends ProxyEntity<TRProxy, TR>, TR> EntityQueryable<TRProxy, TR> limitSelect(long offset, long rows, SQLFuncExpression1<T1Proxy, TRProxy> selectExpression) {
        return limitSelect(true, offset, rows, selectExpression);
    }

    default <TRProxy extends ProxyEntity<TRProxy, TR>, TR> EntityQueryable<TRProxy, TR> limitSelect(boolean condition, long offset, long rows, SQLFuncExpression1<T1Proxy, TRProxy> selectExpression) {
        return this.limit(condition, offset, rows).select(t -> t).select(selectExpression);
    }

    default <TR> Query<TR> limitSelect(long offset, long rows, Class<TR> resultClass, SQLFuncExpression1<T1Proxy, SQLSelectAsExpression> selectExpression) {
        return limitSelect(true, offset, rows, resultClass, selectExpression);
    }

    default <TR> Query<TR> limitSelect(boolean condition, long offset, long rows, Class<TR> resultClass, SQLFuncExpression1<T1Proxy, SQLSelectAsExpression> selectExpression) {
        return this.limit(condition, offset, rows).select(t -> t).select(resultClass, selectExpression);
    }
//    default <TPropertyProxy extends ProxyEntity<TPropertyProxy, TProperty>, TProperty> EntityQueryable<T1Proxy, T1> include(SQLFuncExpression1<SQLKtNavigateIncludeImpl<T1>, ProxyQueryable<TPropertyProxy, TProperty>> navigateIncludeSQLExpression) {
//        return include(true, navigateIncludeSQLExpression);
//    }
//
//    default <TPropertyProxy extends ProxyEntity<TPropertyProxy, TProperty>, TProperty> EntityQueryable<T1Proxy, T1> include(boolean condition, SQLFuncExpression1<SQLKtNavigateIncludeImpl<T1>, ProxyQueryable<TPropertyProxy, TProperty>> navigateIncludeSQLExpression) {
//        if (condition) {
//            getClientQueryable().<TProperty>include(navigateInclude -> navigateIncludeSQLExpression.apply(new SQLKtNavigateIncludeImpl<>(navigateInclude)).getClientQueryable());
//        }
//        return this;
//    }

}
