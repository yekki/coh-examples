#Grid Read Configuration
![](http://docs.oracle.com/middleware/1212/coherence/COHIG/img/tlcgd_jd_004_read-read.png)

1. The application issues a JPQL query
2. TopLink executes a Filter on Coherence cache
3. TopLink returns results from the Coherence cache only, the database is not required

---
#Steps
1. Enable the SCOTT user in Oracle XE. 
2. Run com.yekki.coh.grid.CreateDatabase to drop and create tables
3. Run CacheServer
4. Run com.yekki.coh.grid.InsertExample to persist some objects
5. Run com.yekki.coh.grid.QueryExample to query objects