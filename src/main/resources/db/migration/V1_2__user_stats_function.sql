drop function if exists get_user_stats_average_buys(boolean);

create or replace function get_user_stats_average_buys(is_deleted_param boolean) returns double precision
    language sql
as
$$ select avg(buys)
   from phoneshop.users
   where is_deleted = is_deleted_param;
$$;

alter function get_user_stats_average_buys(boolean) owner to postgres;