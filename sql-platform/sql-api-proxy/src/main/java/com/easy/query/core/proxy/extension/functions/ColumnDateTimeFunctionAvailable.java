package com.easy.query.core.proxy.extension.functions;

import com.easy.query.core.expression.lambda.SQLFuncExpression2;
import com.easy.query.core.expression.parser.core.available.TableAvailable;
import com.easy.query.core.func.SQLFunc;
import com.easy.query.core.func.SQLFunction;
import com.easy.query.core.func.def.enums.DateTimeDurationEnum;
import com.easy.query.core.func.def.enums.DateTimeUnitEnum;
import com.easy.query.core.func.def.enums.TimeUnitEnum;
import com.easy.query.core.proxy.PropColumn;
import com.easy.query.core.proxy.PropTypeColumn;
import com.easy.query.core.proxy.core.EntitySQLContext;
import com.easy.query.core.proxy.core.Expression;
import com.easy.query.core.proxy.extension.functions.cast.ColumnFunctionCastDateTimeAvailable;
import com.easy.query.core.proxy.extension.functions.cast.ColumnFunctionCastStringAvailable;
import com.easy.query.core.proxy.extension.functions.executor.ColumnFunctionCompareComparableDateTimeChainExpression;
import com.easy.query.core.proxy.extension.functions.executor.ColumnFunctionCompareComparableNumberChainExpression;
import com.easy.query.core.proxy.extension.functions.executor.ColumnFunctionCompareComparableStringChainExpression;
import com.easy.query.core.proxy.extension.functions.executor.filter.ColumnFunctionCompareComparableDateTimeFilterChainExpression;
import com.easy.query.core.proxy.extension.functions.executor.filter.impl.ColumnFunctionCompareComparableDateTimeFilterChainExpressionImpl;
import com.easy.query.core.proxy.extension.functions.executor.impl.ColumnFunctionCompareComparableDateTimeChainExpressionImpl;
import com.easy.query.core.proxy.extension.functions.executor.impl.ColumnFunctionCompareComparableNumberChainExpressionImpl;
import com.easy.query.core.proxy.extension.functions.executor.impl.ColumnFunctionCompareComparableStringChainExpressionImpl;
import com.easy.query.core.proxy.impl.duration.DurationExpression;
import com.easy.query.core.proxy.predicate.aggregate.DSLSQLFunctionAvailable;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * create time 2023/12/24 00:10
 * 文件说明
 *
 * @author xuejiaming
 */
public interface ColumnDateTimeFunctionAvailable<TProperty> extends ColumnObjectFunctionAvailable<TProperty, ColumnFunctionCompareComparableDateTimeChainExpression<TProperty>>,
        ColumnAggregateFilterFunctionAvailable<TProperty, ColumnFunctionCompareComparableDateTimeFilterChainExpression<TProperty>>,
        ColumnFunctionCastStringAvailable<TProperty>,
        ColumnFunctionCastDateTimeAvailable<TProperty> {

    @Override
    default ColumnFunctionCompareComparableDateTimeFilterChainExpression<TProperty> max() {
        return createFilterChainExpression(this.getCurrentEntitySQLContext(), this, this.getTable(), this.getValue(), (self, fx) -> {
            return fx.max(x -> {
                PropTypeColumn.columnFuncSelector(x, self);
            });
        }, getPropertyType());
    }

    @Override
    default ColumnFunctionCompareComparableDateTimeFilterChainExpression<TProperty> min() {
        return createFilterChainExpression(this.getCurrentEntitySQLContext(), this, this.getTable(), this.getValue(), (self, fx) -> {
            return fx.min(x -> {
                PropTypeColumn.columnFuncSelector(x, self);
            });
        }, getPropertyType());
    }

    default ColumnFunctionCompareComparableStringChainExpression<String> format(String javaFormat) {
        return new ColumnFunctionCompareComparableStringChainExpressionImpl<>(this.getCurrentEntitySQLContext(), this.getTable(), this.getValue(), fx -> {
            if (this instanceof DSLSQLFunctionAvailable) {
                SQLFunction sqlFunction = ((DSLSQLFunctionAvailable) this).func().apply(fx);
                return fx.dateTimeFormat(x -> x.sqlFunc(sqlFunction), javaFormat);
            } else {
                return fx.dateTimeFormat(this.getValue(), javaFormat);
            }
        }, String.class);
    }

    /**
     * 最小精度为秒部分数据库支持秒以下精度
     * 请使用{@link #plus(long, TimeUnitEnum)}
     *
     * @param duration
     * @param timeUnit
     * @return
     */
    @Deprecated
    default ColumnFunctionCompareComparableDateTimeChainExpression<TProperty> plus(long duration, TimeUnit timeUnit) {
        return new ColumnFunctionCompareComparableDateTimeChainExpressionImpl<>(this.getCurrentEntitySQLContext(), this.getTable(), this.getValue(), fx -> {
            if (this instanceof DSLSQLFunctionAvailable) {
                SQLFunction sqlFunction = ((DSLSQLFunctionAvailable) this).func().apply(fx);
                return fx.plusDateTime(sqlFunction, duration, timeUnit);
            } else {
                return fx.plusDateTime(this.getValue(), duration, timeUnit);
            }
        }, getPropertyType());
    }

    default ColumnFunctionCompareComparableDateTimeChainExpression<TProperty> plus(long duration, TimeUnitEnum timeUnit) {
        return new ColumnFunctionCompareComparableDateTimeChainExpressionImpl<>(this.getCurrentEntitySQLContext(), this.getTable(), this.getValue(), fx -> {
            return fx.plusDateTime2(selector -> {
                PropTypeColumn.columnFuncSelector(selector, this);
                selector.value(duration);
            }, timeUnit);
        }, getPropertyType());
    }

    default <T extends Number> ColumnFunctionCompareComparableDateTimeChainExpression<TProperty> plus(PropTypeColumn<T> durationColumn, TimeUnitEnum timeUnit) {
        return new ColumnFunctionCompareComparableDateTimeChainExpressionImpl<>(this.getCurrentEntitySQLContext(), this.getTable(), this.getValue(), fx -> {
            return fx.plusDateTime2(selector -> {
                PropTypeColumn.columnFuncSelector(selector, this);
                PropTypeColumn.columnFuncSelector(selector, durationColumn);
            }, timeUnit);
        }, getPropertyType());
    }

    /**
     * 增加天
     *
     * @param day
     * @return
     */
    default ColumnFunctionCompareComparableDateTimeChainExpression<TProperty> plusDays(long day) {
        return plus(day, TimeUnitEnum.DAYS);
    }

    /**
     * 增加天
     *
     * @param day
     * @return
     */
    default <T extends Number> ColumnFunctionCompareComparableDateTimeChainExpression<TProperty> plusDays(PropTypeColumn<T> day) {
        return plus(day, TimeUnitEnum.DAYS);
    }

    /**
     * 增加月
     *
     * @param month
     * @return
     */
    default ColumnFunctionCompareComparableDateTimeChainExpression<TProperty> plusMonths(long month) {
        return plus(month, TimeUnitEnum.MONTHS);
    }

    /**
     * 增加月
     *
     * @param month
     * @return
     */
    default <T extends Number> ColumnFunctionCompareComparableDateTimeChainExpression<TProperty> plusMonths(PropTypeColumn<T> month) {
        return plus(month, TimeUnitEnum.MONTHS);
    }

    /**
     * 增加年
     *
     * @param year
     * @return
     */
    default ColumnFunctionCompareComparableDateTimeChainExpression<TProperty> plusYears(long year) {
        return plus(year, TimeUnitEnum.YEARS);
    }

    /**
     * 增加年
     *
     * @param year
     * @return
     */
    default <T extends Number> ColumnFunctionCompareComparableDateTimeChainExpression<TProperty> plusYears(PropTypeColumn<T> year) {
        return plus(year, TimeUnitEnum.YEARS);
    }

    /**
     * 增加秒
     *
     * @param seconds
     * @return
     */
    default ColumnFunctionCompareComparableDateTimeChainExpression<TProperty> plusSeconds(long seconds) {
        return plus(seconds, TimeUnitEnum.SECONDS);
    }

    /**
     * 增加秒
     *
     * @param seconds
     * @return
     */
    default <T extends Number> ColumnFunctionCompareComparableDateTimeChainExpression<TProperty> plusSeconds(PropTypeColumn<T> seconds) {
        return plus(seconds, TimeUnitEnum.SECONDS);
    }

    /**
     * 增加分钟
     *
     * @param minutes
     * @return
     */
    default ColumnFunctionCompareComparableDateTimeChainExpression<TProperty> plusMinutes(long minutes) {
        return plus(minutes, TimeUnitEnum.MINUTES);
    }

    /**
     * 增加分钟
     *
     * @param minutes
     * @return
     */
    default <T extends Number> ColumnFunctionCompareComparableDateTimeChainExpression<TProperty> plusMinutes(PropTypeColumn<T> minutes) {
        return plus(minutes, TimeUnitEnum.MINUTES);
    }

    /**
     * 增加小时
     *
     * @param hours
     * @return
     */
    default ColumnFunctionCompareComparableDateTimeChainExpression<TProperty> plusHours(long hours) {
        return plus(hours, TimeUnitEnum.HOURS);
    }

    /**
     * 增加小时
     *
     * @param hours
     * @return
     */
    default <T extends Number> ColumnFunctionCompareComparableDateTimeChainExpression<TProperty> plusHours(PropTypeColumn<T> hours) {
        return plus(hours, TimeUnitEnum.HOURS);
    }

    /**
     * 天在一年中的时间
     *
     * @return
     */
    default ColumnFunctionCompareComparableNumberChainExpression<Integer> dayOfYear() {
        return dateTimeProp(DateTimeUnitEnum.DayOfYear);
    }

    /**
     * 星期0-6为0-6星期日为0
     *
     * @return 返回指定日期
     */
    default ColumnFunctionCompareComparableNumberChainExpression<Integer> dayOfWeek() {
        return dateTimeProp(DateTimeUnitEnum.DayOfWeek);
    }

    /**
     * 星期1-7为1-7星期日为7
     *
     * @return 返回指定日期
     */
    default ColumnFunctionCompareComparableNumberChainExpression<Integer> dayOfWeekSunDayIsLastDay() {
        return dateTimeProp(DateTimeUnitEnum.DayOfWeekSunDayLastDay);
    }

    /**
     * yyyy 指定时间的年份
     *
     * @return 返回当前年份
     */
    default ColumnFunctionCompareComparableNumberChainExpression<Integer> year() {
        return dateTimeProp(DateTimeUnitEnum.Year);
    }

    /**
     * MM 1-12
     *
     * @return 返回1到12表示月份
     */
    default ColumnFunctionCompareComparableNumberChainExpression<Integer> month() {
        return dateTimeProp(DateTimeUnitEnum.Month);
    }

    /**
     * dd 1-31
     *
     * @return 返回1-31表示在月份中的天数
     */
    default ColumnFunctionCompareComparableNumberChainExpression<Integer> day() {
        return dateTimeProp(DateTimeUnitEnum.Day);
    }

    /**
     * HH 24小时制0-23
     *
     * @return 返回0-23表示小时
     */
    default ColumnFunctionCompareComparableNumberChainExpression<Integer> hour() {
        return dateTimeProp(DateTimeUnitEnum.Hour);
    }

    /**
     * mm 0-59
     *
     * @return
     */
    default ColumnFunctionCompareComparableNumberChainExpression<Integer> minute() {
        return dateTimeProp(DateTimeUnitEnum.Minute);
    }

    /**
     * ss 0-59
     *
     * @return
     */
    default ColumnFunctionCompareComparableNumberChainExpression<Integer> second() {
        return dateTimeProp(DateTimeUnitEnum.Second);
    }
    /**
     * 通用返回时间属性的方法
     *
     * @param dateTimeUnitEnum 时间枚举
     * @return 返回时间属性的表达式
     */
    default ColumnFunctionCompareComparableNumberChainExpression<Integer> dateTimeProp(DateTimeUnitEnum dateTimeUnitEnum) {
        PropColumn propColumn = this;
        return new ColumnFunctionCompareComparableNumberChainExpressionImpl<>(this.getCurrentEntitySQLContext(), this.getTable(), this.getValue(), fx -> {
            if (propColumn instanceof DSLSQLFunctionAvailable) {
                SQLFunction sqlFunction = ((DSLSQLFunctionAvailable) propColumn).func().apply(fx);
                return fx.dateTimeProperty(sqlFunction, dateTimeUnitEnum);
            } else {
                return fx.dateTimeProperty(propColumn.getValue(), dateTimeUnitEnum);
            }
        }, Integer.class);
    }

    /**
     * 新版本和老版本结果相反更符合java的{@link Duration}的类
     * 新版本是a大于b则返回负数,如果a小于b则返回正数
     * 新版本是a大于b则返回负数,如果a小于b则返回正数
     * 新版本是a大于b则返回负数,如果a小于b则返回正数
     * 请使用单入参函数{@link #duration(ColumnDateTimeFunctionAvailable)}
     * 当前方法是a.duration(b,DateTimeDurationEnum.DAYS)
     * 如果a大于b则返回正数,如果a小于b则返回负数
     *
     * @param otherDateTime 被比较的时间
     * @param durationEnum  返回相差枚举比如天数
     * @return 如果为负数表示
     */
    @Deprecated
    default ColumnFunctionCompareComparableNumberChainExpression<Long> duration(ColumnDateTimeFunctionAvailable<TProperty> otherDateTime, DateTimeDurationEnum durationEnum) {
        return new ColumnFunctionCompareComparableNumberChainExpressionImpl<>(this.getCurrentEntitySQLContext(), this.getTable(), this.getValue(), fx -> {
            if (this instanceof DSLSQLFunctionAvailable) {
                SQLFunction sqlFunction = ((DSLSQLFunctionAvailable) this).func().apply(fx);
                if (otherDateTime instanceof DSLSQLFunctionAvailable) {
                    DSLSQLFunctionAvailable otherFunction = (DSLSQLFunctionAvailable) otherDateTime;
                    SQLFunction otherDateTimeFunction = otherFunction.func().apply(fx);
                    return fx.duration(sqlFunction, otherDateTimeFunction, durationEnum);
                } else {
                    return fx.duration(sqlFunction, otherDateTime, otherDateTime.getValue(), durationEnum);
                }
            } else {
                if (otherDateTime instanceof DSLSQLFunctionAvailable) {
                    DSLSQLFunctionAvailable otherFunction = (DSLSQLFunctionAvailable) otherDateTime;
                    SQLFunction otherDateTimeFunction = otherFunction.func().apply(fx);
                    return fx.duration(this.getValue(), otherDateTimeFunction, durationEnum);
                } else {
                    return fx.duration(this.getValue(), otherDateTime, otherDateTime.getValue(), durationEnum);
                }
            }
        }, Long.class);
    }

    /**
     * 新版本和老版本结果相反更符合java的{@link Duration}的类
     * 新版本是a大于b则返回负数,如果a小于b则返回正数
     * 新版本是a大于b则返回负数,如果a小于b则返回正数
     * 新版本是a大于b则返回负数,如果a小于b则返回正数
     * 请使用单入参函数{@link #duration(LocalDateTime)}
     * 当前方法是a.duration(b,DateTimeDurationEnum.DAYS)
     * 如果a大于b则返回正数,如果a小于b则返回负数
     *
     * @param otherDateTime 被比较的时间
     * @param durationEnum  返回相差枚举比如天数
     * @return 如果为负数表示
     */
    @Deprecated
    default ColumnFunctionCompareComparableNumberChainExpression<Long> duration(LocalDateTime otherDateTime, DateTimeDurationEnum durationEnum) {
        return new ColumnFunctionCompareComparableNumberChainExpressionImpl<>(this.getCurrentEntitySQLContext(), this.getTable(), this.getValue(), fx -> {
            if (this instanceof DSLSQLFunctionAvailable) {
                SQLFunction sqlFunction = ((DSLSQLFunctionAvailable) this).func().apply(fx);
                return fx.duration(sqlFunction, otherDateTime, durationEnum);
            } else {
                return fx.duration(this.getValue(), otherDateTime, durationEnum);
            }
        }, Long.class);
    }

    /**
     * a.duration(b).toDays()
     * a比b少多少天,如果a小于b则返回正数,如果a大于b则返回负数
     * 两个日期a,b之间相隔多少天如果不需考虑时间则请使用abs函数保证肯定是正数
     *
     * @param after 被比较的时间
     * @return 后续duration操作
     */
    default DurationExpression duration(ColumnDateTimeFunctionAvailable<TProperty> after) {
        return new DurationExpression(this, after);
    }

    /**
     * a.duration(b).toDays()
     * a比b少多少天,如果a小于b则返回正数,如果a大于b则返回负数
     * 两个日期a,b之间相隔多少天如果不需考虑时间则请使用abs函数保证肯定是正数
     *
     * @param after 被比较的时间
     * @return 后续duration操作
     */
    default DurationExpression duration(LocalDateTime after) {
        return new DurationExpression(this, Expression.of(this.getEntitySQLContext()).constant(after));
    }

    /**
     * a.duration(b).toDays()
     * a比b少多少天,如果a小于b则返回正数,如果a大于b则返回负数
     * 两个日期a,b之间相隔多少天如果不需考虑时间则请使用abs函数保证肯定是正数
     *
     * @param after 被比较的时间
     * @return 后续duration操作
     */
    default DurationExpression duration(LocalDate after) {
        return new DurationExpression(this, Expression.of(this.getEntitySQLContext()).constant(after));
    }

    /**
     * a.duration(b).toDays()
     * a比b少多少天,如果a小于b则返回正数,如果a大于b则返回负数
     * 两个日期a,b之间相隔多少天如果不需考虑时间则请使用abs函数保证肯定是正数
     *
     * @param after 被比较的时间
     * @return 后续duration操作
     */
    default DurationExpression duration(Date after) {
        return new DurationExpression(this, Expression.of(this.getEntitySQLContext()).constant(after));
    }


    @Override
    default ColumnFunctionCompareComparableDateTimeChainExpression<TProperty> createChainExpression(EntitySQLContext entitySQLContext, TableAvailable table, String property, Function<SQLFunc, SQLFunction> func, Class<?> propType) {
        return new ColumnFunctionCompareComparableDateTimeChainExpressionImpl<>(this.getCurrentEntitySQLContext(), this.getTable(), this.getValue(), func, getPropertyType());
    }

    @Override
    default ColumnFunctionCompareComparableDateTimeFilterChainExpression<TProperty> createFilterChainExpression(EntitySQLContext entitySQLContext, PropTypeColumn<?> self, TableAvailable table, String property, SQLFuncExpression2<PropTypeColumn<?>, SQLFunc, SQLFunction> func, Class<?> propType) {
        return new ColumnFunctionCompareComparableDateTimeFilterChainExpressionImpl<>(this.getCurrentEntitySQLContext(), this, this.getTable(), this.getValue(), func, getPropertyType());
    }
}
