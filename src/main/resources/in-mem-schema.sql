CREATE TABLE expense_control (
	exp_pk INT NOT NULL AUTO_INCREMENT,
	exp_id varchar(36) NOT NULL,
	exp_price decimal(15,2) NOT NULL,
	exp_description varchar(255),
	exp_date date NOT NULL
);

INSERT INTO expense_control  (
    	exp_id,
    	exp_price,
    	exp_description,
    	exp_date
) VALUES (
    random_uuid(),
    290.65,
    'any_description',
    CURRENT_DATE()
),
(
    random_uuid(),
    185.45,
    'any_description_second_expense',
    CURRENT_DATE()
),
 (
     random_uuid(),
     206.87,
     'any_description_third_expense',
     CURRENT_DATE()
 ),
 (
     random_uuid(),
     134.87,
     'any_description_fourth_expense',
     CURRENT_DATE()
   );