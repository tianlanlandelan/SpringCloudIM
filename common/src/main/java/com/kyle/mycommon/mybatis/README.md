### 假设场景：When we need?

``` java
    public class User{
        //id
        private int id;
        //名字
        private String name;
        //性别
        private int gender;
        //国家、地区
        private String region;
    }

```

### 预期目标：What we want?
#### Insert
- 基础插入语句：Insert
#### Delete
- 根据ID删除：DeleteById
- 根据条件删除：DeleteByName、DeleteByNameAndGender
#### Update
- 根据ID更新：UpdateById
- 根据条件更新：UpdateByName、UpdateByNameAndGender
#### Select
- 根据ID查询：SelectById
- 查询全量数据：SelectAll
- 根据条件查询：SelectByName、SelectByNameAndGender
- 查询全部记录条数：SelectCount
- 查询满足指定条件的记录条数：SelectCountByName、SelectCountByNameAndGender
  - 查询和你名字相同的人有多少
- 分页查询：SelectPageList
- 带条件的分页查询：SelectPageListByName、SelectPageListByNameAndGender

### 所需数据：What we need?
#### 表名

#### 字段名

#### 可以用来作为查询条件的字段名

#### 分页参数

### 设计思路：How we do it?


### 性能优化：What we can do better?
