package org.apframework.hutool.system;

import cn.hutool.system.SystemUtil;

public class SystemUtilSample {
    public static void main(String[] args) {
        System.out.println(SystemUtil.getJvmSpecInfo());
        System.out.println("----");
        System.out.println(SystemUtil.getJvmInfo());
        System.out.println("----");
        System.out.println(SystemUtil.getJavaSpecInfo());
        System.out.println("----");
        System.out.println(SystemUtil.getJavaInfo());
        System.out.println("----");
        System.out.println(SystemUtil.getJavaRuntimeInfo());
        System.out.println("----");
        System.out.println(SystemUtil.getOsInfo());
        System.out.println("----");
        System.out.println(SystemUtil.getUserInfo());
        System.out.println("----");
        System.out.println(SystemUtil.getHostInfo());
        System.out.println("----");
        System.out.println(SystemUtil.getRuntimeInfo());
    }
}
