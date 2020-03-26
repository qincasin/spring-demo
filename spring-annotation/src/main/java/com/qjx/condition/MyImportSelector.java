package com.qjx.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

/**
 * Created by qincasin on 2020/3/27.
 * 自定义逻辑返回需要导入的组件
 */
public class MyImportSelector implements ImportSelector {
    /**
     *
     * @param importingClassMetadata 当前标注@Import注解的类的所有注解信息
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        Set<String> annotationTypes = importingClassMetadata.getAnnotationTypes();
        String[] interfaceNames = importingClassMetadata.getInterfaceNames();
        String[] memberClassNames = importingClassMetadata.getMemberClassNames();
        String superClassName = importingClassMetadata.getSuperClassName();
        return new String[]{"com.qjx.bean.Yello","com.qjx.bean.Blue"};
    }
}
