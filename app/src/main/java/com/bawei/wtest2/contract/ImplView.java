package com.bawei.wtest2.contract;

import com.bawei.wtest2.entity.Result;

/**
 * Create by ysn
 * TIME: 2019/7/11
 * Doing:契约类
 *       相当于DataCall
 */
public interface ImplView<T> {

    void success(Result result);

    void fail(Result result);
}
