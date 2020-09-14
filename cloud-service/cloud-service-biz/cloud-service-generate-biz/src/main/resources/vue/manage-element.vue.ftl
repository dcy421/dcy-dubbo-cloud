<template>
  <div class="app-container">
    <!-- 查询条件 -->
    <el-form ref="queryParams" :model="queryParams" inline label-width="60px" label-position="left">
      <#list table.fields as field>
      <#if !field.keyFlag>
      <el-form-item label="${field.comment}">
        <el-input v-model="queryParams.${field.propertyName}" clearable placeholder="请输入${field.comment}"/>
      </el-form-item>
      </#if>
      </#list>
      <el-form-item label-width="0px">
        <el-button icon="el-icon-search" type="primary" @click="refresh()">查询</el-button>
        <el-button icon="el-icon-refresh" type="primary" @click="reset()">重置</el-button>
        <el-button icon="el-icon-plus" type="primary" @click="add${cfg.modules}()">添加</el-button>
        <el-button icon="el-icon-delete" type="primary" @click="removeBatch()" :disabled="delBtnDisabled">删除</el-button>
      </el-form-item>
    </el-form>

    <!-- 表格 -->
    <table-page
      ref="tablePage"
      url="${cfg.modulesApi}/page"
      :columns="columns"
      :query-params="queryParams"
      @table-select-val="selectVal">
      <template slot-scope="{ row }" slot="action">
        <el-button @click="update(row)" type="text" >修改</el-button>
        <el-button @click="remove(row)" type="text" >删除</el-button>
      </template>
    </table-page>

    <!-- 添加表单 -->
    <el-dialog
      :title="dialogTitle"
      :visible.sync="dialogShow"
      :close-on-click-modal="false"
      @close="handleReset('form')">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <#list table.fields as field>
          <#if !field.keyFlag>
          <el-form-item label="${field.comment}" prop="${field.propertyName}">
              <el-input v-model="form.${field.propertyName}" placeholder="请输入${field.comment}" @input="change($event)"/>
          </el-form-item>
          </#if>
          </#list>
      </el-form>
      <!-- 自定义按钮组 -->
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleReset('form')">取 消</el-button>
        <el-button type="primary" @click="handleSubmit('form')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import TablePage from "_c/CommonForm/table-page";
  import {add${cfg.modules}, deleteBatch${cfg.modules}ById, delete${cfg.modules}ById, update${cfg.modules}} from "_a/admin/${cfg.modulesApi}/${cfg.modulesApi}";
  import { MessageError, MessageSuccess, ConfirmCustom } from '_l/message'

  export default {
    name: '${cfg.modulesApi}-manage',
    components: { TablePage },
    data() {
      return {
        columns: [
          <#list table.fields as field>
          <#if !field.keyFlag>
          {label: '${field.comment}', prop: '${field.propertyName}'},
          </#if>
          </#list>
          { label: '操作', slot: 'action'}
        ],
        queryParams: {},
        dialogShow:false,
        delBtnDisabled:true,
        dialogTitle: '添加${cfg.modulesName}',
        form: {},
        rules: {
          <#list table.fields as field>
          <#if !field.keyFlag>
          ${field.propertyName}: [this.$ruler('${field.comment}')],
          </#if>
          </#list>
        }
      }
    },
    methods: {
      change(e) {
        this.$forceUpdate()
      },
      /**
       * 刷新
       */
      refresh() {
        this.$refs.tablePage.refresh();
      },
      /**
       * 重置搜索条件
       */
      reset(){
        this.queryParams = {}
      },
      /**
       * 添加弹出框
       */
      add${cfg.modules}(){
        this.form = {};
        this.dialogTitle = '添加${cfg.modulesName}';
        this.dialogShow = true;
      },
      /**
       * 修改弹出框
       * @param row
       */
      update(row){
        this.form = {...row};
        this.dialogTitle = '修改${cfg.modulesName}';
        this.dialogShow = true;
      },
      /**
       * 点击每一行的checkbox
       */
      selectVal(ids) {
        this.ids = ids.map(val => val['${cfg.modulesApi}Id']);
        this.delBtnDisabled = !this.ids.length
      },
      /**
       * 批量删除
       */
      removeBatch() {
        ConfirmCustom({type:'warning'}).then(()=>{
          deleteBatch${cfg.modules}ById(this.ids).then(res => {
            if (res.data) {
              MessageSuccess('del')
            } else {
              MessageError('del')
            }
            this.refresh()
          })
        })
      },
      /**
       * 行内删除
       * @param row
       */
      remove(row) {
        ConfirmCustom({type:'warning'}).then(()=>{
          delete${cfg.modules}ById(row.${cfg.modulesApi}Id).then(res => {
            if (res.data) {
              MessageSuccess('del')
            } else {
              MessageError('del')
            }
            // 刷新表格
            this.refresh()
          })
        })
      },
      /**
       * 重置表单
       * @param name
       */
      handleReset(name) {
        this.dialogShow = false;
        this.$refs[name].resetFields()
      },
      /**
       * 提交表单
       * @param name
       */
      handleSubmit(name) {
        this.$refs[name].validate((valid) => {
          if (valid) {
            if (this.dialogTitle === '添加${cfg.modulesName}') {
              add${cfg.modules}(this.form).then(res => {
                if (res.data) {
                  MessageSuccess('add')
                } else {
                  MessageError('add')
                }
                this.cancelDialogAndRefresh()
              })
            } else {
              update${cfg.modules}(this.form).then(res => {
                if (res.data) {
                  MessageSuccess('upd')
                } else {
                  MessageError('upd')
                }
                this.cancelDialogAndRefresh()
              })
            }
          }
        })
      },
      /**
       * 关闭弹出框 和 刷新表格
       */
      cancelDialogAndRefresh() {
        // 清空表单
        this.handleReset('form');
        // 刷新表格
        this.refresh()
      },
    },
    mounted() {

    }
  }
</script>

<style scoped lang="scss">

</style>
