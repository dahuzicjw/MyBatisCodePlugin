package com.yn.code.generate;

import com.yn.code.model.ConfigModel;
import com.yn.code.model.ControllerGenerateInfo;
import com.yn.code.model.TableInfo;
import com.yn.code.util.CommonUtil;
import com.yn.code.util.FreeMarkUtil;
import com.yn.code.util.MyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 这里是类描述
 *
 * @author : yangning
 * @date: 2018-6-11
 **/
public class ControllerGenerate {
    private ConfigModel configModel;
    private TableInfo tableInfo;

    public ControllerGenerate(ConfigModel configModel, TableInfo tableInfo) {
        this.configModel = configModel;
        this.tableInfo = tableInfo;
    }

    public void generate() throws MyException{
        ControllerGenerateInfo controllerGenerateInfo = new ControllerGenerateInfo();
        controllerGenerateInfo.setAuthor(configModel.getAuthor());
        controllerGenerateInfo.setModuleName(CommonUtil.getNameUpperCamel(configModel.getSign()));
        controllerGenerateInfo.setModuleNameLower(CommonUtil.getNameLowerCamel(configModel.getSign()));
        controllerGenerateInfo.setBasePackage(CommonUtil.getPackageNameByPath(configModel.getControllerPath()));
        controllerGenerateInfo.setServicePackage(CommonUtil.getPackageNameByPath(configModel.getServicePath()));
        controllerGenerateInfo.setModelPackage(CommonUtil.getPackageNameByPath(configModel.getModelPath()));
        controllerGenerateInfo.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
//        controllerGenerateInfo.setModelNameUpperCamel(CommonUtil.getNameUpperCamel(tableInfo.getTableName()));
//        controllerGenerateInfo.setModelNameLowerCamel(CommonUtil.getNameLowerCamel(tableInfo.getTableName()));
//        controllerGenerateInfo.setQueryObjectNameLowerCamel(CommonUtil.getNameLowerCamel(configModel.getSign())+"Query");
        controllerGenerateInfo.setTableComment(tableInfo.getTableComment());
//        controllerGenerateInfo.setTableName(tableInfo.getTableName());
        controllerGenerateInfo.setBaseRequestMapping(configModel.getSign());

        Map<String, Object> root = new HashMap<>(1);
        root.put("controllerGenerateInfo", controllerGenerateInfo);
        String fileName = controllerGenerateInfo.getModuleName() + "Controller.java";
        FreeMarkUtil.generateFile(root, "controller.ftl", configModel.getControllerPath(), fileName);
    }
}
