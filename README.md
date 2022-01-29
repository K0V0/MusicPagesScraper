# MusicPagesScraper

**MusicPagesScraper** is rewrite of [BandzoneScraper](https://github.com/K0V0/BandzoneScraper) 
into Java & Spring Boot with intention to make universal and easy extensible REST API for scraping content from webpages
and extracting useful data.



## Functions

Current version supports performing search and querying list of ***bands*** and it's ***band profile**s* with tracks.  
API performs database caching of results for some given time to dramatically increase speed and reduce processing power 
cost and bandwidth.  
Java version 8 or later and Spring Boot framework is required to compile.  
Live application runs on [http://172.104.155.216:4000/](http://172.104.155.216:4000/) 
or [http://kovo.space:4000/](http://kovo.space:4000/)  

## Supported platforms list

| Platform (site) | Slug |
| --------------- | ---- |
| Bandzone.cz | bandzone |
| FreeTeknoMusic.org | freeteknomusic |

## Usage

### Request
Requests are always performed using ``GET`` HTTP method to given resource. In table below is the list of servers to reach API.

| Server address | Port |
| --------------- |------|
| http://172.104.155.216 | 4000 |
| http://kovo.space | 4000 |

### Request endpoints  

#### Query list of bands

```
[GET] <SERVER_ADDRESS:PORT>/<PLATFORM>/bands?q=<BAND_NAME>&p=<PAGE_NUMBER>
```
| Parameter   | Type     | Defaults if optional param omited                    |
|-------------|----------|------------------------------------------------------|
| PLATFORM    | required | -                                                    | 
| BAND_NAME   | optional | displays list of bands based on provider preferences |     
| PAGE_NUMBER | optional | first page                                           |

```
[GET] <SERVER_ADDRESS:PORT>/bands?q=<BAND_NAME>&p=<PAGE_NUMBER>&s=<PLATFORM_1>&s=<PLATFORM_2>...&s=<PLATFORM_N>
```
| Parameter   | Type     | Defaults if optional param omited                    |
| --- |----------|------------------------------------------------------ |
| BAND_NAME   | optional | displays list of bands based on provider preferences  |     
| PAGE_NUMBER | optional | first page                                            |
| PLATFORM    | optional | all supported platforms will be searched              | 

#### Query band profile

```
[GET] <SERVER_ADDRESS:PORT>/<PLATFORM>/band?q=<SLUG>
```
| Parameter | Type     | Note                                                                                 |
|-----------|----------|--------------------------------------------------------------------------------------|
| PLATFORM  | required | -                                                                                    |     
| SLUG      | required | Unique identifier of band obtained from bands list, see ***Response*** section below | 

### Response

Responses comes as standart ``JSON`` string with corresponding ``HTTP`` code. In following examples are listed basic 
(required) return parameters which should be returned from every platform for given scenario.  
Optional parameters for each platform will be listed below.

#### Response of query for bands list:
```
{
    currentPageItemsCount: <NUMBER>,
    pagesCount: <NUMBER>,
    currentPageNum: <NUMBER>,
    totalItemsCount: <NUMBER>,
    bands: [
        {
            tracks: <NULL>,
            platform: <STRING>,
            title: <STRING>,
            href: <STRING>,
            slug: <STRING>
            ...
        },
        ...
    ]
}
```
| Parameter             | Type | Meaning                                                                           | Note                                                                 |
|-----------------------|------|-----------------------------------------------------------------------------------|----------------------------------------------------------------------|
| currentPageItemsCount | Number | Number of bands listed on current set                                             |                                                                      |
| pagesCount            | Number | Total number of sets                      | 1 even if no bands found and empty set returned                      |
| currentPageNum        | Number | Number of current set | Numbering starts with 1 and value is 1 even if no bands are returned |
| totalItemsCount       | Number | Count of all bands in all sets                                                    |                                                                      |
| bands                 | Array | Array holding set of Bands,                                   | Only one occurence                                                   |
| tracks                | Array | Array holding tracks of given band                         | EMPTY in this query                                                  |
| platform              | Text | Platform which band comes from                                                    |                                                                      |
| title                 | Text | Name of band                                                                      |                                                                      |
| slug | Text | Unique identificator for given band                                               |                                                                      |

#### List of extra parameters for band objects in bands list for given platform:

| Platform | Parameter | Type | Meaning                                | Note                                             |
|-------|-----------| --- |----------------------------------------|--------------------------------------------------|
| bandzone | imageUrl  | Text | Avatar image of given band             | Various sizes and quality, dependent on provider |
|       | genre     | Text | Music genre                            |                                                  |
|       | city      | Text | City where band comes from / have base |                                                  |

#### Response of query for band profile:
```
{
    "slug": <STRING>,
    "platform": <STRING>,
    "title": <STRING>,
    "tracks": [
        {
            "title": <STRING>,
            "slugRef": <STRING>,
            "href": <STRING>,
            "hrefHash": <STRING>,
            ...
        },
        ...
    ]
}
```
| Parameter | Type | Meaning                                   | Note |
|----------|------|-------------------------------------------|------| 
| slug     | Text | Unique identificator for given band       |      |
| platform | Text | Platform which band comes from            |      | 
| title    | Text | Name of band                              |      | 
| tracks   | Array | Array holding tracks of given band        | if band have no tracks, then EMPTY  |
| title    | Text | Name of track                             |      | 
| slugRef  | Text | Reference to unique identificator of band |      | 
| href     | Text | Link to song                              |      | 
| hrefHash | Text | MD5 sum of song's link                    |      | 

#### List of extra parameters for track objects in track list for given band:

| Platform | Parameter        | Type | Meaning                                                     | Note                |
|-------|------------------| --- |-------------------------------------------------------------|---------------------|
| bandzone | albumReleaseYear | Text | Year of album release of given track   | Can be empty string |
|       | albumLabel       | Text | Label/recording studio which released album | Can be empty string                    |
|       | albumTitle       | Text | Title of album |     Can be empty string                | 

## Development.log

| Day         | Task(s) of the day |
|-------------| --- |
| 20.mar.2021 | only base structure of the app |
| 21.mar.2021 | added Bandzone.cz support, readmes for repo, deploying app on VPS |
| 22.mar.2021 | support for Freeteknomusic.org - only bands listing part |
| 24.mar.2021 | support for Freeteknomusic.org done, folders loaded recursive, refactor of scrapers to use interfaces instead objects |
| 07.jun.2021 | started working on ver2.0 with support for scraping multiple pages on one request - code refactor, only endpoint for querying bands from one platform working yet |
| 14.jun.2021 | scraping multiple pages done, found and resolved bug on freeteknomusic.org scraper |
| 27.jun.2021 | resolved bug reporting wrong items count for pages other than first, fixed bandzone bug that allow to query infinite page number displaying last page, DRYied services, made factory design for scrapers |
| 09.jul.2021 | exception handling, bandzone no bands bug resolved |
| 08.jan.2022 | fix due to changes in bandzone webpage |
| 09.jan.2022 | refactor of some concepts of app, null pointer safety precautions |
| 29.jan.2022 | documentation |