USE `blog`;

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog`(
	`blog_id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`blog_title` varchar(100) NOT NULL,
	`blog_category_id` int(11) NOT NULL,
	`blog_category_name` varchar(50) NOT NULL,
	`blog_tags` varchar(100) NOT NULL,
	`blog_status` tinyint(4) NOT NULL DEFAULT '0',
	`enable_comment` tinyint(4) NOT NULL DEFAULT '1',
	`blog_views` bigint(20) NOT NULL DEFAULT '0',
	`content` mediumtext NOT NULL,
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
)ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `admin_user`;

CREATE TABLE `admin_user`(
	`admin_user_id` int(11) NOT NULL AUTO_INCREMENT,
	`login_user_name` varchar(50) NOT NULL,
	`login_password` varchar(50) NOT NULL,
	`nick_name` varchar(50) NOT NULL,
	PRIMARY KEY(`admin_user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE  IF EXISTS `category`;

CREATE TABLE `category`(
	`category_id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`category_name` varchar(50) NOT NULL,
	`category_icon` varchar(50) NOT NULL,
	`category_rank` int(11) NOT NULL DEFAULT 0,
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag`(
	`tag_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`tag_name` varchar(50) NOT NULL,
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `config`;

CREATE TABLE `config`(
	`config_name`  varchar(50) NOT NULL PRIMARY KEY,
	`config_value` varchar(100) NOT NULL DEFAULT '',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4; 


DROP TABLE IF EXISTS `link`;

CREATE TABLE `link`(
	`link_id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`link_type` tinyint(4) NOT NULL DEFAULT 0,
	`link_name` varchar(50) NOT NULL,
	`link_url` varchar(100) NOT NULL,
	`link_description` varchar(200) NOT NULL,
	`link_rank` int(11) NOT NULL DEFAULT 0,
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;