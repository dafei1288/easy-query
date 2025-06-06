package com.easy.query.core.enums;


/**
 * @Description: 文件说明
 * @Date: 2023/2/13 21:40
 * @author xuejiaming
 */
public enum SQLPredicateCompareEnum implements SQLPredicateCompare {

    IN("IN"),
    NOT_IN("NOT IN"),
    LIKE("LIKE"),
    NOT_LIKE("NOT LIKE"),
    EQ("="),
    NE("<>"),
    GT(">"),
    GE(">="),
    LT("<"),
    LE("<="),
    IS_NULL("IS NULL"),
    IS_NOT_NULL("IS NOT NULL"),

    EXISTS("EXISTS"),
    NOT_EXISTS("NOT EXISTS");
    private final String compare;

    SQLPredicateCompareEnum(String compare){

        this.compare = compare;
    }

    @Override
    public String getSQL() {
        return compare;
    }

    @Override
    public SQLPredicateCompare toReverse() {
        switch (this){
            case IN:return NOT_IN;
            case NOT_IN:return IN;
            case LIKE:return NOT_LIKE;
            case NOT_LIKE:return LIKE;
            case EQ:return NE;
            case NE:return EQ;
            case GT:return LE;
            case LE:return GT;
            case GE:return LT;
            case LT:return GE;
            case IS_NULL:return IS_NOT_NULL;
            case IS_NOT_NULL:return IS_NULL;
            case EXISTS:return NOT_EXISTS;
            case NOT_EXISTS:return EXISTS;
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isLike(){
        return SQLPredicateCompareEnum.LIKE == this || SQLPredicateCompareEnum.NOT_LIKE == this;
    }
}
