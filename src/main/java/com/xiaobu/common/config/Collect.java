package com.xiaobu.common.config;

/**
 * 单条任务流程状态值，表sd_collect
 */

public enum Collect {

    /**
     * 已开始
     * @param value
     */
    IS_START(1),

    /**
     * 暂存
     */
    HOLD(2),

    /**
     * 待检验
     * @param value
     */
    FOR_CHECKOUT(3),

    /**
     * 检验失败
     * @param value
     */
    CHECKOUT_ERROR(4),

    /**
     * 检验成功
     * @param value
     */
    CHECKOUT_PASS(5),

    /**
     * 待审核
     * @param value
     */
    FOR_INSPECTION(6),

    /**
     * 审核失败
     * @param value
     */
    INSPECTION_ERROR(7),

    /**
     * 审核成功
     * @param value
     */
    INSPECTION_PASS(8),

    /**
     * 验收失败
     * @param value
     */
    ACCEPTANCE_ERROR(9),

    /**
     * 验收成功
     * @param value
     */
    ACCEPTANCE_SUCCESS(10),

    /**
     * 已结算
     * @param value
     */
    IS_CLOSE_ACCOUNT(11);

    private final Integer value;
    private Collect(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return this.value;
    }


    public String toString() {
        return this.value.toString();
    }
}
