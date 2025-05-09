package com.easy.query.core.expression.sql.include;

import com.easy.query.core.basic.api.select.ClientQueryable;
import com.easy.query.core.expression.lambda.Property;
import com.easy.query.core.expression.lambda.PropertySetterCaller;
import com.easy.query.core.expression.lambda.SQLFuncExpression;
import com.easy.query.core.metadata.IncludeNavigateParams;

import java.util.List;
import java.util.Map;

/**
 * create time 2023/7/21 14:13
 * 文件说明
 *
 * @author xuejiaming
 */
public class IncludeParseContext {
    private final IncludeNavigateParams includeNavigateParams;
    private String[] selfProperties;
    private String[] targetProperties;
    private String[] directMapping;
    private String navigatePropertyName;
    private Class<?> navigateOriginalPropertyType;
    private Class<?> navigatePropertyType;
    private PropertySetterCaller<Object> navigatePropertySetter;
    private Property<Object,?> navigatePropertyGetter;

    private ClientQueryable<?> includeQueryable;
    private SQLFuncExpression<ClientQueryable<?>> includeQueryableExpression;
//    private SQLFuncExpression<ClientQueryable<?>> includeMappingQueryableFunction;

    private List<Object> mappingRows;
    public IncludeParseContext(IncludeNavigateParams includeNavigateParams){

        this.includeNavigateParams = includeNavigateParams;
    }

    public String[] getSelfProperties() {
        return selfProperties;
    }

    public void setSelfProperties(String[] selfProperties) {
        this.selfProperties = selfProperties;
    }

    public String getNavigatePropertyName() {
        return navigatePropertyName;
    }

    public void setNavigatePropertyName(String navigatePropertyName) {
        this.navigatePropertyName = navigatePropertyName;
    }

    public ClientQueryable<?> getIncludeQueryable() {
        return includeQueryable;
    }

    public void setIncludeQueryable(ClientQueryable<?> includeQueryable) {
        this.includeQueryable = includeQueryable;
    }
//
//    public SQLFuncExpression<ClientQueryable<?>> getIncludeMappingQueryableFunction() {
//        return includeMappingQueryableFunction;
//    }
//
//    public void setIncludeMappingQueryableFunction(SQLFuncExpression<ClientQueryable<?>> includeMappingQueryableFunction) {
//        this.includeMappingQueryableFunction = includeMappingQueryableFunction;
//    }

    public List<Object> getMappingRows() {
        return mappingRows;
    }

    public void setMappingRows(List<Object> mappingRows) {
        this.mappingRows = mappingRows;
    }

    public IncludeNavigateParams getIncludeNavigateParams() {
        return includeNavigateParams;
    }

    public Class<?> getNavigateOriginalPropertyType() {
        return navigateOriginalPropertyType;
    }

    public void setNavigateOriginalPropertyType(Class<?> navigateOriginalPropertyType) {
        this.navigateOriginalPropertyType = navigateOriginalPropertyType;
    }

    public Class<?> getNavigatePropertyType() {
        return navigatePropertyType;
    }

    public void setNavigatePropertyType(Class<?> navigatePropertyType) {
        this.navigatePropertyType = navigatePropertyType;
    }

    public String[] getTargetProperties() {
        return targetProperties;
    }

    public void setTargetProperties(String[] targetProperties) {
        this.targetProperties = targetProperties;
    }

    public PropertySetterCaller<Object> getNavigatePropertySetter() {
        return navigatePropertySetter;
    }

    public void setNavigatePropertySetter(PropertySetterCaller<Object> navigatePropertySetter) {
        this.navigatePropertySetter = navigatePropertySetter;
    }

    public Property<Object,?> getNavigatePropertyGetter() {
        return navigatePropertyGetter;
    }

    public void setNavigatePropertyGetter(Property<Object,?> navigatePropertyGetter) {
        this.navigatePropertyGetter = navigatePropertyGetter;
    }

    public SQLFuncExpression<ClientQueryable<?>> getIncludeQueryableExpression() {
        return includeQueryableExpression;
    }

    public void setIncludeQueryableExpression(SQLFuncExpression<ClientQueryable<?>> includeQueryableExpression) {
        this.includeQueryableExpression = includeQueryableExpression;
    }

    public String[] getDirectMapping() {
        return directMapping;
    }

    public void setDirectMapping(String[] directMapping) {
        this.directMapping = directMapping;
    }
}
