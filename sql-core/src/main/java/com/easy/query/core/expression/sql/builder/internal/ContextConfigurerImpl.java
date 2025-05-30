package com.easy.query.core.expression.sql.builder.internal;

import com.easy.query.core.expression.sql.builder.ExpressionContext;

import java.util.function.Function;

/**
 * create time 2024/7/4 08:40
 * 文上下文配置
 *
 * @author xuejiaming
 */
public class ContextConfigurerImpl implements ContextConfigurer {
    private final ExpressionContext expressionContext;

    public ContextConfigurerImpl(ExpressionContext expressionContext) {

        this.expressionContext = expressionContext;
    }

    @Override
    public EasyBehavior getBehavior() {
        return expressionContext.getBehavior();
    }

    @Override
    public void setGroupSize(Integer groupSize) {
        expressionContext.setGroupSize(groupSize);
    }

    @Override
    public Integer getGroupSize() {
        return expressionContext.getGroupSize();
    }

    @Override
    public void setResultSizeLimit(long resultSizeLimit) {
        expressionContext.setResultSizeLimit(resultSizeLimit);
    }

    @Override
    public long getResultSizeLimit() {
        return expressionContext.getResultSizeLimit();
    }

    @Override
    public Boolean getPrintSQL() {
        return expressionContext.getPrintSQL();
    }

    @Override
    public void setPrintSQL(Boolean printSQL) {
        expressionContext.setPrintSQL(printSQL);
    }

    @Override
    public void setPrintNavSQL(Boolean printSQL) {
        expressionContext.setPrintNavSQL(printSQL);
    }

    @Override
    public Boolean getPrintNavSQL() {
        return expressionContext.getPrintNavSQL();
    }

    @Override
    public void setConfigureArgument(Object arg) {
        expressionContext.setConfigureArgument(arg);
    }

    @Override
    public Object getConfigureArgument() {
        return expressionContext.getConfigureArgument();
    }

    @Override
    public void setReverseOrder(boolean reverseOrder) {
        expressionContext.setReverseOrder(reverseOrder);
    }
}
