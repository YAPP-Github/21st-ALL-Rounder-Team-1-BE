CREATE TABLE `Store` (
                         `id` int PRIMARY KEY AUTO_INCREMENT,
                         `user_id` int,
                         `name` varchar(45),
                         `status` Enum('READY', 'VIEW'),
                         `latitude` varchar(45),
                         `longitude` varchar(45),
                         `business_hour` varchar(45),
                         `address` varchar(45),
                         `insta_account` varchar(45),
                         `call_number` varchar(15),
                         `recommendation` int,
                         `registration_number` varchar(30),
                         `is_day_off` boolean,
                         `created_at` datetime,
                         `modified_at` datetime,
                         `removed_at` datetime
);