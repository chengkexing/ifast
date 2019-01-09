CREATE TABLE `b_t_books` (
`id` int NOT NULL AUTO_INCREMENT,
`book_number` varchar(255) NULL COMMENT '图书编号',
`book_name` varchar(255) NULL COMMENT '图书名称',
`book_introduction` varchar(255) NULL COMMENT '图书简介',
`book_sort` int NULL COMMENT '排序序号',
`create_time` datetime NULL,
`create_user_id` int NULL DEFAULT 创建人id,
`book_type_id` int NULL DEFAULT 图书类型id,
`department_id` int NULL COMMENT '部门id',
PRIMARY KEY (`id`) 
);

CREATE TABLE `b_t_books_cover` (
`id` int NOT NULL,
`book_cover_sort` int(255) NULL COMMENT '封面顺序',
`cover_file_name` varchar(255) NULL COMMENT '文件路径名',
`cover_old_name` varchar(255) NULL COMMENT '文件上传名称',
`book_id` int NULL DEFAULT book 编号,
PRIMARY KEY (`id`) 
);

CREATE TABLE `b_t_books_content` (
`id` int NOT NULL AUTO_INCREMENT,
`book_id` int NULL COMMENT '图书编号',
`book_content_sort` int NULL COMMENT '图书序号',
`book_content_introduction` varchar(255) NULL COMMENT '图书说明',
`book_file_name` varchar(255) NULL,
`book_old_name` varchar(255) NULL COMMENT '文件上传名称',
PRIMARY KEY (`id`) 
);

