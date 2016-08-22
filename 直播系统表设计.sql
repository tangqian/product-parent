
CREATE TABLE `live_room` (
  `id` int(9) NOT NULL,
  `name` varchar(255) NOT NULL COMMENT '直播主题名称',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `speaker_id` int(9) NOT NULL COMMENT '主播id(举办公司)',
  `publish_mode` tinyint(3) NOT NULL DEFAULT '0' COMMENT '直播模式,0:视频/ppt',
  `watch_mode` tinyint(3) NOT NULL DEFAULT '0' COMMENT '观看模式,0:游客',
  `is_show` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否显示,0:是',
  `is_contact_show` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否显示联络方式,0:是',
  `industry` varchar(255) NOT NULL COMMENT '行业,用,id1,id2,格式保存',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `cover_id` int(9) DEFAULT NULL COMMENT '封面图id',
  `summary` varchar(1000) DEFAULT NULL COMMENT '摘要',
  `detail` text DEFAULT NULL COMMENT '详细介绍',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '直播房间状态。0:新建,1:保留,2:进行中,3:保留,4:结束',
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='直播房间';

CREATE TABLE `live_room_data` (
  `id` int(9) NOT NULL,
  `room_id` int(9) NOT NULL COMMENT '直播房间id',
  `source_id` int(9) NOT NULL COMMENT '数据源id',
  `sort` int(9) DEFAULT NULL COMMENT '排序',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房间资料表';

CREATE TABLE `live_room_data_public` (
  `id` int(9) NOT NULL,
  `room_id` int(9) NOT NULL COMMENT '直播房间id',
  `source_id` int(9) NOT NULL COMMENT '数据源id',
  `name` varchar(255) NOT NULL COMMENT '文件名',
  `file_id` int(9) NOT NULL COMMENT '文件id',
  `sort` int(9) DEFAULT NULL COMMENT '排序',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房间资料表(审核通过)';

CREATE TABLE `live_room_speech` (
  `id` int(9) NOT NULL,
  `room_id` int(9) NOT NULL COMMENT '直播房间id',
  `source_id` int(9) NOT NULL COMMENT '数据源id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房间演讲稿表';

CREATE TABLE `live_room_speech_public` (
  `id` int(9) NOT NULL,
  `room_id` int(9) NOT NULL COMMENT '直播房间id',
  `source_id` int(9) NOT NULL COMMENT '数据源id',
  `name` varchar(255) NOT NULL COMMENT '文件名',
  `file_id` int(9) NOT NULL COMMENT '文件id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房间演讲稿表(审核通过)';

CREATE TABLE `live_room_video` (
  `id` int(9) NOT NULL,
  `room_id` int(9) NOT NULL COMMENT '直播房间id',
  `source_id` int(9) NOT NULL COMMENT '数据源id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房间视频表';

CREATE TABLE `live_room_video_public` (
  `id` int(9) NOT NULL,
  `room_id` int(9) NOT NULL COMMENT '直播房间id',
  `source_id` int(9) NOT NULL COMMENT '数据源id',
  `name` varchar(255) NOT NULL COMMENT '视频名',
  `detail` varchar(1000) DEFAULT NULL COMMENT '详细介绍',
  `file_id` int(9) NOT NULL COMMENT '视频文件id',
  `cover_id` int(9) NOT NULL COMMENT '视频封面图id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房间视频表(审核通过)';

CREATE TABLE `live_room_chat` (
  `id` int(9) NOT NULL,
  `room_id` int(9) NOT NULL COMMENT '直播房间id',
  `send_by` int(9) NOT NULL COMMENT '发送者id',
  `content` varchar(1000) NOT NULL COMMENT '聊天内容',
  `type` tinyint(3) NOT NULL COMMENT '聊天类型(1:私聊,2:群聊)',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房间聊天记录表';

CREATE TABLE `live_room_chat_receiver` (
  `id` int(9) NOT NULL,
  `chat_id` int(9) NOT NULL COMMENT '聊天记录id',
  `receive_by` int(9) NOT NULL COMMENT '接收者id',
  `read_date` datetime DEFAULT NULL COMMENT '阅读时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='聊天接收记录表';

CREATE TABLE `live_room_rich_text` (
  `id` int(9) NOT NULL,
  `room_id` int(9) NOT NULL COMMENT '直播房间id',
  `content` varchar(2000) NOT NULL COMMENT '图文内容',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '状态,0:新建',
  `create_by` int(9) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` int(9) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='直播图文表';

CREATE TABLE `live_report_data_download` (
  `id` int(9) NOT NULL,
  `room_id` int(9) NOT NULL COMMENT '直播房间id',
  `type` tinyint(3) NOT NULL COMMENT '资料类型(1:资料,2:演讲稿)',
  `data_id` int(9) NOT NULL COMMENT '主播资料id',
  `user_id` int(9) NOT NULL COMMENT '用户id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_report_room_data_user_id` (`room_id`,`type`,`data_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房间资料下载记录表';

CREATE TABLE `live_report_user_present` (
  `id` int(9) NOT NULL,
  `room_id` int(9) NOT NULL COMMENT '直播房间id',
  `user_id` int(9) NOT NULL COMMENT '用户id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_report_room_user_id` (`room_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户访问记录表';

CREATE TABLE `live_report_visitor_present` (
  `id` int(9) NOT NULL,
  `room_id` int(9) NOT NULL COMMENT '直播房间id',
  `visitor_id` int(9) NOT NULL COMMENT '游客id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_report_room_visitor_id` (`room_id`,`visitor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='游客访问记录表';

CREATE TABLE `live_user` (
  `id` int(9) NOT NULL,
  `account` varchar(64) NOT NULL COMMENT '用户名',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `type` tinyint(3) NOT NULL COMMENT '用户类型(1:观众,2:管理员,3:主播,4:客服)',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '用户状态。0:正常,1禁用',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_account_type` (`account`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

CREATE TABLE `live_visitor` (
  `id` int(9) NOT NULL,
  `ip` varchar(255) NOT NULL COMMENT '访问ip',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='游客表';

CREATE TABLE `live_speaker` (
  `id` int(9) NOT NULL,
  `company` varchar(255) NOT NULL COMMENT '公司名',
  `address` varchar(255) DEFAULT NULL COMMENT '公司详细地址',
  `industry` varchar(255) NOT NULL COMMENT '行业,用,id1,id2,格式保存',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `job` varchar(64) DEFAULT NULL COMMENT '职位',
  `logo_id` int(9) DEFAULT NULL COMMENT '头像id',
  `sex` tinyint(3) DEFAULT '0' COMMENT '性别，0:保密,1:男,2:女',
  `department` varchar(64) DEFAULT NULL COMMENT '部门',
  `mobile_phone` varchar(64) DEFAULT NULL COMMENT '手机',
  `telephone` varchar(64) DEFAULT NULL COMMENT '座机',
  `fax` varchar(64) DEFAULT NULL COMMENT '传真',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主播表';

CREATE TABLE `live_speaker_data` (
  `id` int(9) NOT NULL,
  `speaker_id` int(9) NOT NULL COMMENT '主播id',
  `name` varchar(255) NOT NULL COMMENT '文件名',
  `file_id` int(9) NOT NULL COMMENT '文件id',
  `sort` int(9) DEFAULT NULL COMMENT '排序',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '状态,0:新建(即待审核),1、2:保留,3:审核通过,4:审核不通过',
  `audit_by` varchar(64) DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `audit_reason` varchar(255) DEFAULT NULL COMMENT '审核说明',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主播资料表';

CREATE TABLE `live_speaker_data_recycle` (
  `id` int(9) NOT NULL,
  `speaker_id` int(9) NOT NULL COMMENT '主播id',
  `name` varchar(255) NOT NULL COMMENT '文件名',
  `file_id` int(9) NOT NULL COMMENT '文件id',
  `sort` int(9) DEFAULT NULL COMMENT '排序',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '状态,0:新建(即待审核),1、2:保留,3:审核通过,4:审核不通过',
  `audit_by` varchar(64) DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `audit_reason` varchar(255) DEFAULT NULL COMMENT '审核说明',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主播资料回收表';

CREATE TABLE `live_speaker_speech` (
  `id` int(9) NOT NULL,
  `speaker_id` int(9) NOT NULL COMMENT '主播id',
  `name` varchar(255) NOT NULL COMMENT '文件名',
  `file_id` int(9) NOT NULL COMMENT '文件id',
  `sort` int(9) DEFAULT NULL COMMENT '排序',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '状态,0:新建(即待审核),1、2:保留,3:审核通过,4:审核不通过',
  `audit_by` varchar(64) DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `audit_reason` varchar(255) DEFAULT NULL COMMENT '审核说明',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主播演讲稿表';

CREATE TABLE `live_speaker_speech_recycle` (
  `id` int(9) NOT NULL,
  `speaker_id` int(9) NOT NULL COMMENT '主播id',
  `name` varchar(255) NOT NULL COMMENT '文件名',
  `file_id` int(9) NOT NULL COMMENT '文件id',
  `sort` int(9) DEFAULT NULL COMMENT '排序',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '状态,0:新建(即待审核),1、2:保留,3:审核通过,4:审核不通过',
  `audit_by` varchar(64) DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `audit_reason` varchar(255) DEFAULT NULL COMMENT '审核说明',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主播演讲稿回收表';

CREATE TABLE `live_speaker_video` (
  `id` int(9) NOT NULL,
  `speaker_id` int(9) NOT NULL COMMENT '主播id',
  `name` varchar(255) NOT NULL COMMENT '视频名',
  `detail` varchar(1000) DEFAULT NULL COMMENT '详细介绍',
  `file_id` int(9) NOT NULL COMMENT '视频文件id',
  `cover_id` int(9) NOT NULL COMMENT '视频封面图id',
  `sort` int(9) DEFAULT NULL COMMENT '排序',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '状态,0:新建(即待审核),1、2:保留,3:审核通过,4:审核不通过',
  `audit_by` varchar(64) DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `audit_reason` varchar(255) DEFAULT NULL COMMENT '审核说明',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主播视频表';

CREATE TABLE `live_speaker_video_recycle` (
  `id` int(9) NOT NULL,
  `speaker_id` int(9) NOT NULL COMMENT '主播id',
  `name` varchar(255) NOT NULL COMMENT '视频名',
  `detail` varchar(1000) DEFAULT NULL COMMENT '详细介绍',
  `file_id` int(9) NOT NULL COMMENT '视频文件id',
  `cover_id` int(9) NOT NULL COMMENT '视频封面图id',
  `sort` int(9) DEFAULT NULL COMMENT '排序',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '状态,0:新建(即待审核),1、2:保留,3:审核通过,4:审核不通过',
  `audit_by` varchar(64) DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `audit_reason` varchar(255) DEFAULT NULL COMMENT '审核说明',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主播视频回收表';

CREATE TABLE `live_speaker_recycle_inventory` (
  `id` int(9) NOT NULL,
  `speaker_id` int(9) NOT NULL COMMENT '主播id',
  `subject_name` varchar(255) NOT NULL COMMENT '关联名称',
  `subject_id` int(9) NOT NULL COMMENT '关联实体id',
  `subject_type` int(9) NOT NULL COMMENT '关联实体类型,如视频、演讲稿',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主播回收站清单表';

CREATE TABLE `live_audience` (
  `id` int(9) NOT NULL,
  `company` varchar(255) DEFAULT NULL COMMENT '公司名',
  `country` varchar(64) DEFAULT NULL COMMENT '国家编码',
  `province` varchar(64) DEFAULT NULL COMMENT '省份编码',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `job` varchar(64) DEFAULT NULL COMMENT '职位',
  `logo_id` int(9) DEFAULT NULL COMMENT '头像id',
  `sex` tinyint(3) DEFAULT '0' COMMENT '性别，0:保密,1:男,2:女',
  `department` varchar(64) DEFAULT NULL COMMENT '部门',
  `mobile_phone` varchar(64) DEFAULT NULL COMMENT '手机',
  `telephone` varchar(64) DEFAULT NULL COMMENT '座机',
  `fax` varchar(64) DEFAULT NULL COMMENT '传真',
  `biz_type` tinyint(3) NOT NULL DEFAULT '1' COMMENT '1自注册,2会员,3非会员,4推广',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='观众表';

CREATE TABLE `live_audience_register` (
  `id` int(9) NOT NULL,
  `room_id` int(9) NOT NULL COMMENT '直播房间id',
  `audience_id` int(9) NOT NULL COMMENT '观众id',
  `company` varchar(255) DEFAULT NULL COMMENT '公司名',
  `country` varchar(64) DEFAULT NULL COMMENT '国家编码',
  `province` varchar(64) DEFAULT NULL COMMENT '省份编码',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `job` varchar(64) DEFAULT NULL COMMENT '职位',
  `logo_id` int(9) DEFAULT NULL COMMENT '头像id',
  `sex` tinyint(3) DEFAULT '0' COMMENT '性别，0:保密,1:男,2:女',
  `department` varchar(64) DEFAULT NULL COMMENT '部门',
  `mobile_phone` varchar(64) DEFAULT NULL COMMENT '手机',
  `telephone` varchar(64) DEFAULT NULL COMMENT '座机',
  `fax` varchar(64) DEFAULT NULL COMMENT '传真',
  `is_sent_email` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否已发送通知邮件,0:否',  
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_room_audience_id` (`room_id`,`audience_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='观众登记表';

CREATE TABLE `live_backlist` (
  `id` int(9) NOT NULL,
  `room_id` int(9) NOT NULL COMMENT '直播房间id',
  `user_id` int(9) NOT NULL COMMENT '用户id',
  `reason` varchar(1000) DEFAULT NULL COMMENT '原因',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_backlist_room_user_id` (`room_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户黑名单表';

CREATE TABLE `live_waiter` (
  `id` int(9) NOT NULL,
  `speaker_id` int(9) NOT NULL COMMENT '主播id',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `job` varchar(64) DEFAULT NULL COMMENT '职位',
  `logo_id` int(9) DEFAULT NULL COMMENT '头像id',
  `sex` tinyint(3) DEFAULT '0' COMMENT '性别，0:保密,1:男,2:女',
  `department` varchar(64) DEFAULT NULL COMMENT '部门',
  `mobile_phone` varchar(64) DEFAULT NULL COMMENT '手机',
  `telephone` varchar(64) DEFAULT NULL COMMENT '座机',
  `fax` varchar(64) DEFAULT NULL COMMENT '传真',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客服表';


CREATE TABLE `sys_dict` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` decimal(10,0) NOT NULL COMMENT '排序（升序）',
  `parent_id` varchar(64) DEFAULT '0' COMMENT '父级编号',
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`label`),
  KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

CREATE TABLE `sys_file` (
  `id` int(11) NOT NULL,
  `uri` varchar(225) NOT NULL COMMENT '文件物理相对路径',
  `original_name` varchar(225) NOT NULL COMMENT '原始文件名称',
  `size` int(11) NOT NULL COMMENT '文件大小(字节数)',
  `ext` varchar(64) NOT NULL COMMENT '文件扩展名',
  `is_temp` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否临时文件,0:不是',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '类型,0:图片,1:文件',
  `subject_id` int(9) DEFAULT NULL COMMENT '关联实体id',
  `subject_type` int(9) NOT NULL COMMENT '关联实体类型',
  `create_date` datetime NOT NULL,
  `create_by` int(9) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件表';

CREATE TABLE `sys_email` (
  `id` int(9) NOT NULL,
  `subject` varchar(255) NOT NULL COMMENT '主题',
  `content` text NOT NULL,
  `receiver` varchar(255) NOT NULL,
  `cc` varchar(255) DEFAULT NULL,
  `bcc` varchar(255) DEFAULT NULL,
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '状态,0:待发送,1:发送成功,2:发送失败',
  `subject_id` int(9) NOT NULL COMMENT '关联实体id',
  `subject_type` int(9) NOT NULL COMMENT '关联实体类型',
  `create_date` datetime NOT NULL,
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件记录表';

CREATE TABLE `sys_sequence` (
  `name` varchar(128) NOT NULL COMMENT '序列key',
  `next_id` int(9) NOT NULL DEFAULT '1' COMMENT '下一个可用值',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='序列表';
