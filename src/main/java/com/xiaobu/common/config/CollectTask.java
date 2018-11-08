package com.xiaobu.common.config;

/**
 * 任务包流程状态值，表sd_collect_task
 */
public enum CollectTask {

    /**
     * 已发布
     */
    IS_ISSUE(1),

    /**
     * 已开始
     * @param value
     */
    IS_START(2),

    /**
     * 已满额
     */
    FULFIL_QUOTA(3),

    /**
     * 待检验
     * @param value
     */
    //FOR_CHECKOUT(3),

    /**
     * 检验失败
     * @param value
     */
    //CHECKOUT_ERROR(4),

    /**
     * 检验成功
     * @param value
     */
    //CHECKOUT_PASS(5),

    /**
     * 待审核
     * @param value
     */
    FOR_INSPECTION(4),

    /**
     * 审核失败
     * @param value
     */
    INSPECTION_ERROR(5),

    /**
     * 审核成功
     * @param value
     */
    INSPECTION_PASS(6),

    /**
     * 已满额
     */
    //FULFIL_QUOTA(6),

    /**
     * 待验收
     * @param value
     */
    FOR_ACCEPTANCE(7),

    /**
     * 验收失败
     * @param value
     */
    ACCEPTANCE_ERROR(8),

    /**
     * 验收成功
     * @param value
     */
    ACCEPTANCE_SUCCESS(9),

    /**
     * 已结算
     * @param value
     */
    IS_CLOSE_ACCOUNT(10),

    /**
     *已完结
     * @param value
     */
   // IS_FINISH(10),

    /**
     * 废弃
     * @param value
     */
    DISCARD(11);


    private final Integer value;
    private CollectTask(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return this.value;
    }


    public String toString() {
        return this.value.toString();
    }
}
