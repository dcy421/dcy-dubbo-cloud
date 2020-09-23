<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

<#if enableCache>
    <!-- 开启二级缓存 -->
    <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>

</#if>
<#if baseResultMap>
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
<#list table.fields as field>
<#if field.keyFlag><#--生成主键排在第一位-->
        <id column="${field.name}" property="${field.propertyName}" />
</#if>
</#list>
<#list table.fields as field>
<#if !field.keyFlag><#--生成普通字段 -->
        <result column="${field.name}" property="${field.propertyName}" />
</#if>
</#list>
<#list table.commonFields as field><#--生成公共字段 -->
        <result column="${field.name}" property="${field.propertyName}" />
</#list>
    </resultMap>

</#if>
<#if baseColumnList>
    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
        <#list table.fields as field><#if field.name == field.propertyName>${field.name}<#else>${field.name} AS ${field.propertyName}</#if>,</#list><#list table.commonFields as field><#if field.name == field.propertyName>${field.name}<#else>${field.name} AS ${field.propertyName}</#if><#if table.commonFields?size != field_index+1>,</#if></#list>
    </sql>

    <!-- 通用查询结果列 别名 -->
    <sql id="Base_Column_List_Alias">
        <#list table.fields as field><#if field.name == field.propertyName>${entity?uncap_first}.${field.name}<#else>${entity?uncap_first}.${field.name} AS ${field.propertyName}</#if>,</#list><#list table.commonFields as field><#if field.name == field.propertyName>${entity?uncap_first}.${field.name}<#else>${entity?uncap_first}.${field.name} AS ${field.propertyName}</#if><#if table.commonFields?size != field_index+1>,</#if></#list>
    </sql>
</#if>
</mapper>
