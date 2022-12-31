ALTER TABLE `Item` ADD COLUMN `is_hided` boolean;
ALTER TABLE `Item` ADD COLUMN `is_ready` boolean;

ALTER TABLE `User` ADD COLUMN `email` varchar(45);
ALTER TABLE `User` ADD COLUMN `phone_number` varchar(45);

ALTER TABLE `Store` ADD COLUMN `notice` text;
ALTER TABLE `Store` ADD COLUMN `is_ready` boolean;