select * from survivor;

insert into survivor (name,age,gender,location,inventory,status) 
values ( 'Morgan',29,'Male','Sunnyside','Food','affected');

This is how I manually update survivors details.

update Survivor set location= 'Midrand' where name = 'Morgan';