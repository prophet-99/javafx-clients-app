-- CREATE TABLE
create table clients
(
    code varchar(8) not null constraint clients_pk primary key,
    first_name varchar(255),
    last_name varchar(255),
    email varchar(255),
    phone varchar(15),
    created_at timestamp
);

-- INSERT SOME DATA
insert into clients (code, first_name, last_name, email, phone, created_at) values ('01SCC', 'Sonia', 'Cupressus cashmeriana Carrière', 'sarchbell0@instagram.com', '342-360-9361', '2022-09-12 04:58:47');
insert into clients (code, first_name, last_name, email, phone, created_at) values ('02GGM', 'Goldarina', 'Galium mollugo L.', 'glongland1@hhs.gov', '239-295-7365', '2022-04-06 06:07:17');
insert into clients (code, first_name, last_name, email, phone, created_at) values ('03BEP', 'Barrie', 'Eriogonum plumatella Durand & Hilg.', 'bmccleod2@statcounter.com', '541-328-2300', '2022-12-28 14:37:14');
insert into clients (code, first_name, last_name, email, phone, created_at) values ('04FGE', 'Fara', 'Gutierrezia elegans Al Schneid. & P. Lyon', 'fbuff3@clickbank.net', '237-472-0804', '2022-04-15 12:28:18');
insert into clients (code, first_name, last_name, email, phone, created_at) values ('05JAD', 'Jamal', 'Agave deserti Engelm.', 'jhevey4@wordpress.com', '438-292-9795', '2022-04-19 13:35:55');
insert into clients (code, first_name, last_name, email, phone, created_at) values ('06BRP', 'Brook', 'Ramalina petrina Bowler & Rundel', 'bmcconville5@twitter.com', '996-174-9894', '2022-06-05 06:55:43');
insert into clients (code, first_name, last_name, email, phone, created_at) values ('07GLS', 'Granger', 'Lupinus sericeus Pursh ssp. sericeus var. flexuosus (Lindl. ex J. Agardh) C.P. Sm.', 'gsaile6@usa.gov', '740-695-6831', '2022-12-17 16:03:47');
insert into clients (code, first_name, last_name, email, phone, created_at) values ('08GGG', 'Gwennie', 'Guarea glabra Vahl', 'gquye7@tripadvisor.com', '545-860-5900', '2022-04-13 20:08:17');
insert into clients (code, first_name, last_name, email, phone, created_at) values ('09ASW', 'Amandy', 'Sphaeralcea wrightii A. Gray', 'afarfolomeev8@live.com', '596-915-9948', '2022-12-01 00:35:00');
insert into clients (code, first_name, last_name, email, phone, created_at) values ('10LAI', 'Laryssa', 'Astragalus iodanthus S. Watson var. diaphanoides Barneby', 'lgobel9@weather.com', '250-878-0882', '2022-08-03 07:44:58');
insert into clients (code, first_name, last_name, email, phone, created_at) values ('11RMP', 'Ronda', 'Mallotus philippensis (Lam.) Müll. Arg.', 'rpoulsona@salon.com', '982-985-1574', '2022-12-05 19:39:00');
insert into clients (code, first_name, last_name, email, phone, created_at) values ('12JGV', 'Jan', 'Gratiola virginiana L. var. aestuariorum Pennell', 'jbritnellb@oracle.com', '706-904-6491', '2022-12-01 13:15:51');
insert into clients (code, first_name, last_name, email, phone, created_at) values ('13MTL', 'Malvina', 'Trematodon longicollis Michx.', 'mlinsayc@icio.us', '381-900-1909', '2023-01-07 03:05:06');
insert into clients (code, first_name, last_name, email, phone, created_at) values ('14AOU', 'Abelard', 'Oldenlandia uniflora L.', 'abirtlesd@dot.gov', '321-237-4649', '2022-07-26 11:25:39');
insert into clients (code, first_name, last_name, email, phone, created_at) values ('15LOV', 'Lindsay', 'Opegrapha vulgata Ach.', 'lpurtone@blog.com', '816-498-5668', '2022-12-09 12:53:52');
insert into clients (code, first_name, last_name, email, phone, created_at) values ('16CMP', 'Cookie', 'Muellerella pygmaea (Körb.) D. Hawksw. var. ventosicola (Mudd) Triebel', 'cjahndelf@flickr.com', '773-143-6116', '2023-01-31 13:40:45');
insert into clients (code, first_name, last_name, email, phone, created_at) values ('17RER', 'Regan', 'Eucalyptus redunca Schauer', 'rvoseg@biglobe.ne.jp', '501-554-7551', '2022-03-14 06:06:15');
insert into clients (code, first_name, last_name, email, phone, created_at) values ('18FDA', 'Ferdie', 'Draba alpina L.', 'flechelleh@studiopress.com', '197-197-5008', '2023-02-15 12:07:51');
insert into clients (code, first_name, last_name, email, phone, created_at) values ('19DLN', 'Dulcie', 'Lupinus nipomensis Eastw.', 'ddunkirki@tripadvisor.com', '173-544-9482', '2023-02-16 17:19:57');
insert into clients (code, first_name, last_name, email, phone, created_at) values ('20BLN', 'Britteny', 'Lomatium nevadense (S. Watson) J.M. Coult. & Rose', 'brandalesj@usatoday.com', '876-342-6090', '2022-08-02 10:56:25');

-- SP -> CREATE; UPDATE; DELETE
create or replace procedure sp_create_client(
  c_code varchar(8),
  c_first_name varchar(255),
  c_last_name varchar(255),
  c_email varchar(255),
  c_phone varchar(15)
)
language plpgsql as
$$
  declare
    sc_code varchar(8);
    last_code int;
  begin
    if c_code = '' then
      select count(*) into last_code from clients;
      last_code := last_code + 1;
      sc_code := concat(last_code, upper(substring(c_first_name, 1, 1)), upper(substring(c_last_name, 1, 2)));
      -- INSERT
      insert into clients (code, first_name, last_name, email, phone, created_at) values (sc_code, c_first_name, c_last_name, c_email, c_phone, now());
      raise notice 'Client successfully inserted';
    else
      -- UPDATE
      if exists(select * from clients where code = c_code) then
        update clients set first_name = c_first_name, last_name = c_last_name, email = c_email, phone = c_phone
          where code = c_code;
        raise notice 'Client with % successfully updated', c_code;
      else
        raise exception 'Not found client with this code';
      end if;
    end if;
  end;
$$;

create or replace procedure sp_delete_client(c_code varchar(8))
language plpgsql as
$$
begin
  if exists(select * from clients where code = c_code) then
    delete from clients where code = c_code;
    raise notice 'Client with % successfully deleted', c_code;
  else
    raise exception 'Not found client with this code';
  end if;
end;
$$;

-- QUERIES -> GET ALL, GET ALL BY CODE, NAME, LAST_NAME
select * from clients;
select * from clients where code ilike '' or first_name ilike 'a%' or last_name ilike '';