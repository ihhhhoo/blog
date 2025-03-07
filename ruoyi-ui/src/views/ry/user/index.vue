<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="博客名称" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="博客名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['ry:user:add']"
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
          v-hasPermi="['ry:user:edit']"
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
          v-hasPermi="['ry:user:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ry:user:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="博客名称" align="center" prop="username" />
      <el-table-column label="用户头像" align="center" prop="userImg" >
        <template slot-scope="scope">
          <el-image
            style="width: 120px;height: 60px;"
            :src="scope.row.userImg"
            lazy
            :preview-src-list="[scope.row.userImg]">
          </el-image>
        </template>
      </el-table-column>
      <!--<el-table-column label="主页背景图片" align="center" prop="indexBackImg" />-->
      <!--<el-table-column label="标签列表" align="center" prop="tags" />-->
      <!--<el-table-column label="经纬度" align="center" prop="longitude" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-opened"
            @click="handleAddTagsAndLike(scope.row)"
            v-hasPermi="['ry:blog:edit']"
          >添加爱好</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-opened"
            @click="handleAddContext(scope.row)"
            v-hasPermi="['ry:blog:edit']"
          >添加上下文</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-opened"
            @click="handleAddSocial(scope.row)"
            v-hasPermi="['ry:blog:edit']"
          >链接</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ry:user:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ry:user:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改用户信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户名/博客名称" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名/博客名称" />
        </el-form-item>
        <el-form-item label="用户头像" prop="userImg">
          <div class="tabBlock">
            <imageUpload v-model="form.userImg"/>
          </div>
        </el-form-item>
        <el-form-item label="主页背景图片" prop="indexBackImg">
          <div class="tabBlock">
            <imageUpload v-model="form.indexBackImg"/>
          </div>
        </el-form-item>
        <el-form-item label="卡片背景" prop="drawUrl">
          <div class="tabBlock">
            <imageUpload v-model="form.drawUrl"/>
          </div>
        </el-form-item>
        <el-form-item label="标签列表" prop="tags">
          <el-input v-model="form.tags" placeholder="标签  以','隔开" />
        </el-form-item>
        <el-form-item label="卡片标题" prop="drawTitle">
          <el-input v-model="form.drawTitle" placeholder="请输入卡片标题" />
        </el-form-item>
        <el-form-item label="开始标题" prop="startTitle">
          <el-input v-model="form.startTitle" placeholder="请输入开始标题" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改社交链接对话框 -->
    <el-dialog title="修改链接" :visible.sync="socialLinksOpen" width="700px" append-to-body>
      <el-form ref="form" :model="socialLinks" :rules="rules" label-width="80px">
        <!--<el-button size="mini" type="primary" @click="updateSocialLinks">修改信息</el-button>-->
        <el-form-item v-for="(value,key) in socialLinks" :key="key" :label="key" >
          <el-input v-model="socialLinks[key]" :placeholder="'请输入' + key"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">保 存</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

      <el-dialog title="修改爱好" :visible.sync="TagsAndLikeOpen" width="700px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-position="top" label-width="80px">
          <el-form-item>
            <el-row>
              <el-button size="mini" type="primary" @click="addLike">添加爱好</el-button>
              <el-table :data="form.likes" :border="true" style="width: 99.99%;">
                <el-table-column align="center" min-width="20%" prop="pic" label="标题">
                  <template slot-scope="scope">
                    <el-input v-model="scope.row.title" type="textarea" :rows="6" size="small" />
                  </template>
                </el-table-column>
                <el-table-column align="center" min-width="20%" prop="remark" label="url">
                  <template slot-scope="scope">
                    <image-upload v-model="scope.row.url" />
                  </template>
                </el-table-column>
                <el-table-column align="center" min-width="20%" label="操作">
                  <template slot-scope="scope">
                    <el-button size="mini" type="danger" plain @click="delLike(scope.$index, scope.row)">删除</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-row>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="saveBlogLike">保 存</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>



    <el-dialog :title="title" :visible.sync="contextOpen" :before-close="cancel" width="1200px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="内容">
          <!-- 图片用base64存储,url方式移动端会显示异常 -->
              <CherryMarkdown ref="CherryMarkdown"  :height='400' v-model='form.context' ></CherryMarkdown>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="saveContext">保 存</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
import { listUser, getUser, delUser, addUser, updateUser } from "@/api/ry/user";
import CherryMarkdown from '@/components/CherryMarkdown'

export default {
  components: {
    CherryMarkdown,
  },
  name: "User",
  data() {
    return {
      contextOpen :false,
      // dialogFormVisible :false,
      socialLinks: {
        githubUrl: '',
        gitUrl: '',
        qqUrl: '',
        bilibiliUrl: '',
        csdnUrl: ''
      },
      TagsAndLikeOpen: false,//标签喜欢
      socialLinksOpen: false,
      //添加链接属性
      Open: false,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用户信息表格数据
      userList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        username: null,
        userImg: null,
        loadingImgUrl: null,
        indexBackImg: null,
        like: null,
        tags: null,
        longitude: null,
        drawUrl: null,
        drawTitle: null,
        startTitle: null,
        context: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        username: [
          { required: true, message: "用户名/博客名称不能为空", trigger: " blur" }
        ],
      },

    };
  },
  created() {
    this.getList();
  },
  methods: {
    saveContext(){
      //保存上下文
      console.log(this.form)
      if(this.form != null){
        updateUser(this.form).then(response => {
          this.$modal.msgSuccess("保存成功");
          this.contextOpen = false;
          this.getList();
        });
      }
    },
    saveBlogLike(){
      //保存喜欢
      var tempLikes = this.form.likes
      if(tempLikes.length > 0){
        for (let i = 0; i < tempLikes.length; i++) {
          var like = tempLikes[i]
          if(like.title === ''){
            this.$message({
              type: 'error',
              message: '标题不能为空'
            })
            return false
          }else if(like.url === ''){
            this.$message({
              type: 'error',
              message: '图片不能为空'
            })
            return false
          }
        }
      }
      this.form.like = JSON.stringify(tempLikes)
      if(this.form.id != null){
        updateUser(this.form).then(response => {
          this.$modal.msgSuccess("保存成功");
          this.TagsAndLikeOpen = false;
          this.getList();
        });
      }
    },
    delLike(index,row){
      this.$confirm('确认删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 点击确定的操作(调用接口)
        for (var i = 0; i < this.form.likes.length; i++) {
          if (row.id === this.form.likes[i].id) {
            this.form.likes.splice(i, 1)
          }
        }
      }).catch(() => {
        // 点取消的提示
        return
      })
    },
    addLike(){
      this.form.likes.push({
        title: '',
        url: '',
      })
    },
    handleAddContext(row){
      //添加上下文
      this.reset();
      const id = row.id || this.ids;
      // console.log("id = " , id)
      this.form.id = id;
      getUser(id).then(response =>{
        this.form.context = response.data.context;
        this.contextOpen = true;
      });
    },
    handleAddTagsAndLike(row){
      this.reset();
      //添加喜欢和标签
      const id = row.id || this.id;
      this.form.id = id;
      // 查询已有的相关属性
      getUser(id).then(response =>{
        if(response.data.like !== null && '' !== response.data.like){
          this.form.likes = JSON.parse(response.data.like);
        }
        this.TagsAndLikeOpen = true;
      });
    },

    //添加社交连接
    handleAddSocial(row){
      this.reset();
      // console.log("this.form.sociaLink = " , this.form.)
      const id = row.id || this.id;
      // 查询已有的相关属性
      getUser(id).then(response =>{
        this.form = response.data;
        console.log("this.form=" , this.form)
        if(response.data.socialLinks !== null){
          let temp = response.data.socialLinks;
          this.socialLinks.gitUrl = temp.gitUrl;
          this.socialLinks.githubUrl = temp.githubUrl;
          this.socialLinks.bilibiliUrl = temp.bilibiliUrl;
          this.socialLinks.csdnUrl = temp.csdnUrl;
          this.socialLinks.qqUrl = temp.qqUrl;
        }
        if(response.data.like !== null){
          this.form.like = JSON.parse(response.data.like);
        }
        this.socialLinksOpen = true;
      });
      console.log("this.form2=" , this.form);

    },
    /** 查询用户信息列表 */
    getList() {
      this.loading = true;
      listUser(this.queryParams).then(response => {
        // console.log("respo=" , response);
        // this.socialLinks = response.data.socialLinks;
        this.userList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.socialLinksOpen = false;
      this.TagsAndLikeOpen = false;
      this.contextOpen = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        username: null,
        userImg: null,
        loadingImgUrl: null,
        indexBackImg: null,
        like: null,
        likes: [],
        tags: null,
        longitude: null,
        drawUrl: null,
        drawTitle: null,
        startTitle: null,
        context: null
      };
      this.socialLinks= {
        githubUrl: '',
          gitUrl: '',
          qqUrl: '',
          bilibiliUrl: '',
          csdnUrl: ''
      },
      this.resetForm("form");

      // console.log("socialLinks = ",this.form.socialLinks);
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
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
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加用户信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getUser(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户信息";
      });
      this.open = true;
      this.title = "修改用户信息";
    },
    /** 提交按钮 */
    submitForm() {
      this.form.socialLinks = this.socialLinks;
      console.log("this.form = ",this.form)
      this.$refs["form"].validate(valid => {
        console.log("form = ",this.form);
        if (valid) {
            if (this.form.id != null) {
              updateUser(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.socialLinksOpen = false;//添加社交属性
                this.getList();
              });
            } else {
              addUser(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除用户信息编号为"' + ids + '"的数据项？').then(function() {
        return delUser(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ry/user/export', {
        ...this.queryParams
      }, `user_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

<style scoped lang="scss">
.tabBlock{
  height: 180px;
  margin-top: 20px;
}
.blogPic{
  width: 200px;
  height: 180px;
}
</style>

