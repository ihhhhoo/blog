<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="评论者" prop="content" v-if="isAdmin">
        <el-input v-model="queryParams.createBy" placeholder="请输入评论者" clearable size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="评论内容" prop="content">
        <el-input v-model="queryParams.content" placeholder="请输入评论内容" clearable size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="文章标题" prop="blogTitle">
        <el-input v-model="queryParams.blogTitle" placeholder="请输入文章标题" clearable size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!--<el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['ry:comment:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['ry:comment:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['ry:comment:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ry:comment:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="commentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="父评论id" align="center" prop="parentId" />
      <el-table-column label="主评论id(第一级评论)" align="center" prop="mainId" />
      <el-table-column label="点赞数量" align="center" prop="likeNum" />
      <el-table-column label="内容" align="center" prop="content" />
      <el-table-column label="评论类型：对人评论，对项目评论，对资源评论" align="center" prop="type" />
      <el-table-column label="被评论者id，可以是人、项目、资源" align="center" prop="blogId" />
      <el-table-column label="评论者id" align="center" prop="userId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ry:comment:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ry:comment:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>-->

    <!--<pagination-->
    <!--  v-show="total>0"-->
    <!--  :total="total"-->
    <!--  :page.sync="queryParams.pageNum"-->
    <!--  :limit.sync="queryParams.pageSize"-->
    <!--  @pagination="getList"-->
    <!--/>-->

    <!--&lt;!&ndash; 添加或修改评论对话框 &ndash;&gt;-->
    <!--<el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>-->
    <!--  <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
    <!--    <el-form-item label="父评论id" prop="parentId">-->
    <!--      <el-input v-model="form.parentId" placeholder="请输入父评论id" />-->
    <!--    </el-form-item>-->
    <!--    <el-form-item label="主评论id(第一级评论)" prop="mainId">-->
    <!--      <el-input v-model="form.mainId" placeholder="请输入主评论id(第一级评论)" />-->
    <!--    </el-form-item>-->
    <!--    <el-form-item label="点赞数量" prop="likeNum">-->
    <!--      <el-input v-model="form.likeNum" placeholder="请输入点赞数量" />-->
    <!--    </el-form-item>-->
    <!--    <el-form-item label="内容">-->
    <!--      <editor v-model="form.content" :min-height="192"/>-->
    <!--    </el-form-item>-->
    <!--    <el-form-item label="被评论者id，可以是人、项目、资源" prop="blogId">-->
    <!--      <el-input v-model="form.blogId" placeholder="请输入被评论者id，可以是人、项目、资源" />-->
    <!--    </el-form-item>-->
    <!--    <el-form-item label="删除标志" prop="delFlag">-->
    <!--      <el-input v-model="form.delFlag" placeholder="请输入删除标志" />-->
    <!--    </el-form-item>-->
    <!--    <el-form-item label="评论者id" prop="userId">-->
    <!--      <el-input v-model="form.userId" placeholder="请输入评论者id" />-->
    <!--    </el-form-item>-->
    <!--  </el-form>-->
    <!--  <div slot="footer" class="dialog-footer">-->
    <!--    <el-button type="primary" @click="submitForm">确 定</el-button>-->
    <!--    <el-button @click="cancel">取 消</el-button>-->
    <!--  </div>-->
    <!--</el-dialog>-->

    <el-card>
      <div class="el-card__header" style="text-align: left;padding: 0">
        <h3 style="float: left;margin: 0;">评论列表</h3>
        <right-toolbar style="float: right;" :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </div>
      <ul style="padding: 0;" class="comment-list">
        <li v-show="commentList.length==0"
            style="text-align: center;border-bottom: 1px dashed #ccc;margin: 10px 0;list-style-type:none;">
          <span class="el-table__empty-text">暂无数据</span>
        </li>
        <li class="comment" v-for="mes in commentList" :key="mes.id">
          <el-avatar v-if="mes.avatar!==''&&mes.avatar!=null" :src="mes.avatar"></el-avatar>
          <el-avatar v-else icon="el-icon-user-solid"></el-avatar>
          <div class="content">
            <div style="display: flex;justify-content: space-between;width: 100%">
              <div class="nkname">
                <span class="name">{{mes.createBy}} </span>
                <span class="date">{{mes.createTime}}</span>
                <span v-show="mes.type=='0'" class="rp">给你评论</span>
                <span v-show="mes.type=='1'" class="rp">回复了</span>
                <span v-show="mes.type=='1'" class="pcreateBy">{{mes.parentCreateBy}}</span>
                <span v-show="mes.type=='1'" class="rp">的评论</span>
                <span class="rp">|</span>
                <span class="rp">查看文章：</span>
                <span class="blog" @click="getBlogInfo(mes)">{{mes.blogTitle}}</span>
              </div>
              <div class="op">
                <span @click="getBlogInfo(mes)" class="blog">查看</span>
                <span> | </span>
                <el-button type="text" @click="handleAdd(mes)" v-hasPermi="['cms:comment:add']">回复</el-button>
                <span v-show="!isAdmin&&mes.createBy!=name" style="margin-right: 39.43px;"></span>
                <span v-show="!(!isAdmin&&mes.createBy!=name)"> | </span>
                <span v-show="!(!isAdmin&&mes.createBy!=name)" class="del" @click="handleDelete(mes)">删除</span>
              </div>
            </div>
            <p class="reply">{{mes.content}}</p>
          </div>
        </li>
      </ul>
    </el-card>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改评论管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-input @blur="blur" v-model="form.content" type="textarea" maxlength="100" show-word-limit :placeholder="toName" />
      </el-form>
      <div slot="footer" class="dialog-footer">
        <div style="float: left;">
          <Emoji @output="output"></Emoji>
        </div>
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  mapGetters
} from 'vuex'
import { listComment, getComment, delComment, addComment, updateComment } from "@/api/ry/comment";
import Emoji from '@/components/Emoji'

export default {
  name: "Comment",
  components:{
    Emoji
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      names: [],

      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 评论表格数据
      commentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        parentId: null,
        mainId: null,
        likeNum: null,
        content: null,
        type: null,
        blogId: null,
        userId: null,
        isAdmin: false,
        blogTitle: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      toName:'',
      isAdmin: false,
    };
  },
  computed: {
    ...mapGetters(['name']),
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询评论列表 */
    getList() {
      this.loading = true;
      listComment(this.queryParams).then(response => {
        console.log("responsedata = " , response);
        this.total = response.total;

        this.commentList = response.rows;
        this.total = response.total;
        this.loading = false;
        console.log("response = ",response);
      });
    },

    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        parentId: null,
        mainId: null,
        likeNum: null,
        content: null,
        type: null,
        blogId: null,
        delFlag: null,
        userId: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.queryParams.createBy = "";
      this.queryParams.content = "";
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd(mes) {
      this.reset();
      if(mes.mainId!=null){
        this.form.mainId = mes.mainId
      }else{
        this.form.mainId = mes.id
      }
      this.form.parentId = mes.id
      this.form.blogId = mes.blogId
      this.form.type = '1'
      this.form.createBy = this.$store.getters.name
      this.form.updateBy = this.$store.getters.name;
      this.toName = "@" + mes.createBy
      this.open = true;
      this.title = "回复评论";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getComment(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改评论";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateComment(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addComment(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      let name = row.content || this.names;
      this.$modal.confirm('是否确认删除"' + name + '"这条评论？').then(function() {
        return delComment(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ry/comment/export', {
        ...this.queryParams
      }, `comment_${new Date().getTime()}.xlsx`)
    },
    blur(e){
      this.cursorIndexStart = e.srcElement.selectionStart  // 获取input输入框失去焦点时光标选中开始的位置
      this.cursorIndexEnd = e.srcElement.selectionEnd  // 获取input输入框失去焦点时光标选中结束的位置
    },
    output(val) {
      if (this.cursorIndexStart !== null && this.form.content) {
        //如果 文本域获取了焦点, 则在光标位置处插入对应字段内容
        this.form.content = this.form.content.substring(0, this.cursorIndexStart) + val + this.form.content.substring(this.cursorIndexEnd)
      } else {
        // 如果 文本域未获取焦点, 则在字符串末尾处插入对应字段内容
        this.form.content = this.form.content?this.form.content:'' + val
      }
    },
    //跳转评论页
    getBlogInfo(mes){
      this.$router.push({
        path: '/ry/blog/blogInfo',
        query: {
          id: mes.blogId,
          commentId: mes.id,
        }
      });
    },
  }
};
</script>
<style scoped>
.comment {
  border-bottom: 1px dashed #ccc;
  margin: 10px 0;
  display: flex;
}

.el-avatar {
  width: 35px;
  height: 35px;
  /* border: 2px solid white; */
  box-shadow: 0 0 10px 2px rgba(0, 0, 0, .06);
  flex-shrink: 0;
}

.content {
  text-align: left;
  font-size: 14px;
  flex-grow: 1;
}

.nkname {
  margin-left: 10px;
  max-width: 830px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.rp,
.date {
  color: #999;
  margin-left: 10px;
}

.pcreateBy {
  margin-left: 10px;
}

.blog {
  color: #349edf;
  margin-left: 10px;
  cursor: pointer;
}

.reply {
  margin-left: 10px;
}

.op {
  color: #ddd;
  font-weight: lighter;
}

.rep {
  color: #349edf;
}

.del {
  color: red;
}

.op:hover {
  cursor: pointer;
}

.el-table__empty-text {
  line-height: 60px;
  width: 50%;
  color: #909399;
}
</style>
