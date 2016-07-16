package com.lody.virtual.client.hook.patchs.account;

import android.accounts.Account;

import com.lody.virtual.client.hook.base.Hook;

import java.lang.reflect.Method;

/**
 * @author Lody
 *
 * @see android.accounts.IAccountManager#updateAppPermission(Account, String, int, boolean)
 *
 */

public class Hook_UpdateAppPermission extends Hook<AccountManagerPatch> {

    /**
     * 这个构造器必须有,用于依赖注入.
     *
     * @param patchObject 注入对象
     */
    public Hook_UpdateAppPermission(AccountManagerPatch patchObject) {
        super(patchObject);
    }

    @Override
    public String getName() {
        return "updateAppPermission";
    }

    @Override
    public Object onHook(Object who, Method method, Object... args) throws Throwable {
        AccountUtils.replaceAccount(args);
        if (args[1] instanceof String) {
            args[1] = AccountUtils.ACCOUNT_TYPE;
        }
        return method.invoke(who, args);
    }
}
