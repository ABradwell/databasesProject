# databasesProject
TripAdvisor project for Databases I, Winter Semester, University of Ottawa

To have the same databse on both of our ends, we can use pg_dump after making changes. The other would then use psql dbname < infile to recreate the toher personas database.



we should use  --single-transaction to assure that we dont have half updated databases