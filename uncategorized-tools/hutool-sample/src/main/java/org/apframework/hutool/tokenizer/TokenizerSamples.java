package org.apframework.hutool.tokenizer;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import cn.hutool.extra.tokenizer.Word;
import cn.hutool.extra.tokenizer.engine.hanlp.HanLPEngine;

import java.util.Iterator;

/**
 * pom 包引用，参考：https://github.com/looly/hutool/blob/v5-master/hutool-extra/pom.xml
 */
public class TokenizerSamples {

    public static void defaultTokenizer(){
        //自动根据用户引入的分词库的jar来自动选择使用的引擎
        TokenizerEngine engine = TokenizerUtil.createEngine();

        //解析文本
        String text = "这两个方法的区别在于返回值";
        Result result = engine.parse(text);
        //输出：这 两个 方法 的 区别 在于 返回 值
        String resultStr = CollUtil.join((Iterator<Word>)result, " ");
        System.out.println(resultStr);
    }

    public static void hanLPTokenizer(){
        TokenizerEngine engine = new HanLPEngine();

        //解析文本
        String text = "这两个方法的区别在于返回值";
        Result result = engine.parse(text);
        //输出：这 两个 方法 的 区别 在于 返回 值
        String resultStr = CollUtil.join((Iterator<Word>)result, " ");
        System.out.println(resultStr);
    }

    public static void main(String[] args) {
        defaultTokenizer();
        hanLPTokenizer();
    }
}
