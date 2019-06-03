INSERT INTO public.usr
        (id, email, username, password, firstname, lastname, middlename, age, active)
	VALUES
	      (nextval('user_seq_id'), 'admin', 'admin', 'admin', 'admin', 'admin', 'admin', 30, true),
        (nextval('user_seq_id'), 'almat', 'almat', 'almat', 'almat', 'almat', 'almat', 30, true);

INSERT INTO public.home
        (id, name, address, user_id)
	VALUES
	      (nextval('home_seq_id'), 'almat home', 'balkash', 2);

INSERT INTO public.room
        (id, name, temperature, humidity, light, home_id)
	VALUES
	      (nextval('room_seq_id'), 'living', 22.3, 43.6, 0, 1),
        (nextval('room_seq_id'), 'bed', 22.3, 37.4, 0, 1),
        (nextval('room_seq_id'), 'child', 25.2, 40.1, 0, 1);