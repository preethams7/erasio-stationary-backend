insert ignore accounts set id = 1, tx_email='super@erasio.com', tx_password ='$2a$10$52UR/WEckFIYkHlecUHMfOGY4MKeA1gE5o019TksXt6/AxEzKakaa',fl_disabled=false, role='ROLE_SUPER';
insert ignore authority set id = 1, username='super@erasio.com', role='ROLE_SUPER';
insert ignore features set id=1,tx_feature='LOGIN',fl_active='true';