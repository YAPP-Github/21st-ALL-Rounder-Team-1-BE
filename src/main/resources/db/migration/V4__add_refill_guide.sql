CREATE TABLE `store_refill_guide` (
                         `id` int PRIMARY KEY AUTO_INCREMENT,
                         `store_id` int not null,
                         `img_path` varchar(100) not null,
                         `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                         `modified_at` TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
                         `removed_at` TIMESTAMP
);