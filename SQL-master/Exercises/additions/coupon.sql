DROP TABLE coupon;

CREATE TABLE COUPON (
  coupon_id VARCHAR(8) NOT NULL,
  valid_from DATETIME,
  expires_on DATETIME,
  discount_percent DECIMAL(5,2),
  discount_amount DECIMAL(10,2),
  redeemed BOOLEAN,
 
  PRIMARY KEY(coupon_id)
) ENGINE=InnoDB;

ALTER TABLE orders ADD COLUMN coupon_id VARCHAR(8) AFTER status_id,
	ADD INDEX idx_order_coupon_id (coupon_id), 
	ADD CONSTRAINT fk_coupon_order 
		FOREIGN KEY (coupon_id) 
		REFERENCES coupon(coupon_id)
		ON DELETE RESTRICT
		ON UPDATE CASCADE;
 


INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('ADAK','2018/10/05 01:46:32','2018/11/04 18:55:45',null,126,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('ADVU','2018/08/16 21:37:09','2018/11/22 03:01:55',8,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('AELT','2018/07/26 08:46:18','2018/11/16 19:13:53',null,122,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('AFLL','2018/08/26 16:19:32','2018/10/27 00:57:24',13,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('AIPX','2018/07/14 18:21:11','2018/09/15 12:06:06',null,124,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('AZYK','2018/09/04 02:23:18','2018/11/04 02:57:50',null,202,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('BPAE','2018/07/08 16:15:57','2018/09/06 12:13:43',23,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('BPNE','2018/08/20 00:49:13','2018/12/30 10:54:22',null,227,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('BQCW','2018/12/10 02:45:03','2018/12/12 12:17:29',8,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('CFYY','2018/08/09 23:22:14','2018/08/11 06:59:51',1,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('CREC','2018/08/11 11:29:18','2018/09/29 18:59:26',null,178,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('CSDB','2018/07/17 22:20:07','2018/12/19 15:49:04',30,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('CTLX','2018/07/22 14:19:43','2018/11/10 11:37:30',11,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('DFDV','2018/07/20 07:06:08','2018/10/14 16:39:41',null,182,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('DFSZ','2018/10/15 23:07:57','2018/12/11 22:25:09',null,182,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('DKEL','2018/08/30 12:53:21','2018/12/22 23:11:15',null,231,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('DLNC','2018/08/03 08:35:41','2018/10/18 01:51:39',12,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('DSGE','2018/07/07 20:48:19','2018/12/08 09:22:38',30,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('DWYQ','2018/07/06 07:14:25','2018/10/02 00:34:32',17,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('EEJG','2018/07/23 03:55:47','2018/10/19 19:29:15',null,244,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('EGBK','2018/09/10 13:16:55','2018/09/24 23:47:15',25,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('EMOJ','2018/07/18 01:22:05','2018/07/18 03:50:31',10,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('ENXF','2018/10/25 02:44:15','2018/12/17 21:26:45',27,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('EWKR','2018/07/15 17:24:33','2018/09/11 00:17:46',null,52,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('EWNF','2018/08/12 09:11:58','2018/10/31 07:03:06',19,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('FBUW','2018/09/29 07:10:49','2018/10/10 21:21:58',null,224,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('FCJI','2018/11/18 05:03:04','2018/12/25 18:55:35',null,230,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('FTBO','2018/10/09 13:00:03','2018/12/16 04:40:15',null,227,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('FWVJ','2018/08/12 23:59:37','2018/09/07 12:00:42',null,172,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('FZQM','2018/09/29 12:56:51','2018/10/03 22:27:15',32,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('GHFQ','2018/10/14 12:28:20','2018/11/11 05:14:02',null,186,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('GNSK','2018/11/06 11:44:57','2018/11/29 22:56:31',35,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('GQEF','2018/09/22 04:59:29','2018/10/24 07:54:06',null,211,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('GSFI','2018/08/27 03:46:12','2018/11/02 18:53:53',null,56,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('HICR','2018/07/02 04:38:55','2018/12/27 19:06:10',null,23,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('HZIS','2018/10/02 06:41:42','2018/11/11 17:30:27',null,53,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('IEJJ','2018/10/24 13:31:04','2018/10/29 02:58:17',21,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('IGZJ','2018/08/01 20:33:24','2018/10/27 21:53:06',48,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('IMCQ','2018/07/01 08:34:05','2018/12/07 06:54:02',40,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('JEJS','2018/08/06 08:29:36','2018/11/13 15:53:24',null,151,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('JFKR','2018/10/20 02:56:21','2018/12/10 15:52:59',6,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('JHNW','2018/09/11 04:57:52','2018/12/12 10:41:16',50,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('JJBB','2018/07/02 04:22:15','2018/10/14 14:28:41',null,229,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('KEPL','2018/11/04 12:00:36','2018/11/05 07:24:20',42,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('KIZM','2018/09/12 17:01:41','2018/12/13 06:21:35',17,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('KJXD','2018/07/24 08:56:29','2018/10/07 04:29:32',10,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('KYJR','2018/09/20 14:49:14','2018/10/09 07:36:43',37,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('KYLE','2018/08/27 22:00:38','2018/11/08 22:14:28',null,245,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('LJWD','2018/11/03 16:40:08','2018/11/17 07:34:43',13,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('LLHE','2018/09/02 00:42:01','2018/11/04 02:47:16',21,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('MAUR','2018/08/02 08:26:39','2018/11/18 20:35:30',6,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('MGRO','2018/09/22 23:35:16','2018/11/18 20:16:12',40,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('NBEJ','2018/10/06 19:46:15','2018/10/15 05:31:22',null,203,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('NBKF','2018/09/09 01:31:50','2018/12/06 12:47:38',11,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('NEEY','2018/11/05 11:35:05','2018/11/26 05:32:30',null,76,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('NOZE','2018/09/03 05:01:46','2018/12/22 18:19:38',23,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('NTTQ','2018/07/08 23:39:17','2018/08/16 11:49:36',null,228,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('NWTV','2018/08/04 14:28:37','2018/08/27 05:32:28',null,54,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('NYAI','2018/07/21 05:34:13','2018/08/18 23:56:10',23,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('OHCP','2018/10/10 23:51:36','2018/10/28 01:49:10',null,223,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('OLQX','2018/10/23 06:28:48','2018/10/28 03:30:41',null,238,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('OMWB','2018/07/16 13:08:58','2018/07/29 15:46:15',null,134,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('PJWB','2018/09/08 09:28:12','2018/11/06 21:03:42',32,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('PZLE','2018/08/01 17:08:02','2018/09/22 17:16:23',null,77,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('QBDY','2018/08/18 23:48:47','2018/10/30 12:02:11',41,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('QKCU','2018/10/16 06:49:52','2018/10/26 12:35:48',null,170,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('QODB','2018/07/16 03:00:19','2018/10/23 14:02:36',null,50,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('QRDG','2018/07/06 11:00:23','2018/11/16 12:59:35',null,97,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('QZMM','2018/10/01 00:03:46','2018/12/22 01:01:14',5,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('RDBE','2018/10/19 04:52:24','2018/12/06 20:58:34',null,210,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('RDVW','2018/07/01 12:19:38','2018/09/28 01:42:25',null,175,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('RJFR','2018/12/10 00:34:16','2018/12/28 22:04:38',null,110,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('RKRZ','2018/09/06 10:11:23','2018/11/26 07:11:50',null,81,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('SQMS','2018/08/06 07:34:19','2018/12/04 22:17:25',13,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('SXSP','2018/07/09 13:16:07','2018/12/07 18:36:52',null,222,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('SXUG','2018/10/22 08:35:23','2018/11/19 15:56:05',13,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('THHL','2018/08/21 16:28:51','2018/09/05 23:41:58',null,244,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('TLPI','2018/09/30 02:00:55','2018/11/20 21:20:18',29,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('TZVC','2018/08/12 13:10:57','2018/08/15 19:57:22',48,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('UFHU','2018/07/26 17:02:34','2018/12/23 22:13:49',26,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('ULRX','2018/09/25 19:17:22','2018/11/27 09:12:49',34,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('UTFL','2018/09/27 21:42:28','2018/12/11 10:05:29',28,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('UVPX','2018/12/15 17:14:17','2018/12/18 06:42:44',47,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('UZGH','2018/08/01 08:17:05','2018/09/14 07:36:07',null,248,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('VBQZ','2018/08/28 06:58:09','2018/12/09 11:29:27',null,108,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('VKQN','2018/10/31 05:57:59','2018/11/13 05:50:35',46,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('VUIX','2018/07/16 01:16:37','2018/08/14 07:59:57',null,195,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('VVVE','2018/07/13 07:58:49','2018/10/21 23:03:52',16,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('WASU','2018/09/18 07:51:37','2018/10/31 19:34:22',null,210,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('WLUU','2018/08/28 00:37:48','2018/12/23 14:32:23',50,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('WODB','2018/07/24 23:51:15','2018/09/02 18:18:08',null,238,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('WVPD','2018/09/23 03:23:15','2018/09/29 05:47:29',null,136,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('XAAB','2018/09/09 17:19:52','2018/09/16 19:09:26',2,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('XEFE','2018/07/20 02:01:50','2018/09/23 12:44:40',31,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('XIHR','2018/07/15 17:34:32','2018/09/09 07:32:23',null,181,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('YNUS','2018/12/14 09:58:28','2018/12/17 13:15:36',null,199,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('YQQM','2018/07/30 19:44:57','2018/12/13 10:23:38',9,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('ZMCB','2018/08/06 18:59:39','2018/10/04 18:30:22',17,null,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('ZPDN','2018/11/10 04:27:22','2018/11/20 12:14:17',null,157,0);
INSERT INTO COUPON(coupon_id, valid_from, expires_on, discount_percent, discount_amount, redeemed) VALUES ('ZUXZ','2018/09/11 13:23:10','2018/11/29 14:13:18',null,239,0);
