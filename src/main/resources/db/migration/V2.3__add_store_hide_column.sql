ALTER TABLE `Item` ADD `is_hided` boolean;
ALTER TABLE `Item` ADD `is_ready` boolean;

ALTER TABLE `User` ADD `email` varchar(45);
ALTER TABLE `User` ADD `phone_number` varchar(45);

ALTER TABLE `Store` ADD `notice` text;
ALTER TABLE `Store` ADD `is_ready` boolean;