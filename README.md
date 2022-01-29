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
Requests are always performed using ``GET`` HTTP method to given resource.

### Response

Responses comes as standart ``JSON`` string with corresponding ``HTTP`` code. In following examples are listed basic 
(required) return parameters which should be returned from every platform for given scenario.  
Optional parameters for each platform will be listed below.

#### List of bands query:
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
| Parameter             | Type | Meaning / Note                                                                                |
|-----------------------|------|-----------------------------------------------------------------------------------------------|
| currentPageItemsCount | Number | Number of items listed on current set                                                         |
| pagesCount            | Number | Total number of sets                                                                          |
| currentPageNum        | Number | Number of current set, numbering starts with 1 and value is 1 even if no results are returned |
| totalItemsCount       | Number | Sum of all items in all sets                                                                  |
| bands                 | Array | *Only one occurence*                                                                          |
| tracks                | Array | *NULL by default*                                                                             |
| platform              | Text | Platform which record comes from                                                              |
| title                 | Text | Title of item                                                                                 |
| href | Text | URL of given resource, no special meaning                                                     |
| slug | Text | Unique identificator for given resource |
### GET /{platform_slug}/bands?q={band_title}&p={page_number}

Performs bands search on one given platform 

### Request

| Parameter | Type     | Defaults for optional params                    |
| --- |----------|-------------------------------------------------|
| platform_slug | required | - | 
| band_title | optional | displays list of bands based on provider settings |     
| page_number | optional | first page |

example:
```
http://172.104.155.216:4000/bandzone/bands?q=wild&page=2
```

### Response
```
{
	bands: [
		{
			title: "Wilderness",
			imageUrl: "https://bzmedia.cz/band/wi/wilderness/gallery/profile.default/239095_t_s.jpg",
			href: "https://bandzone.cz/wilderness",
			slug: "wilderness",
			genre: "power-metal",
			city: "Vsetín",
			tracks: [ ]
		},
		{
			title: "The Wilderness",
			imageUrl: "https://bzmedia.cz/band/a6/58/5319/e1/ad/8d4e/NymabjkJbmiHMEBCkjay658emY_CCFGu.jpg",
			href: "https://bandzone.cz/thewildernesstt",
			slug: "thewildernesstt",
			genre: "punk",
			city: "Trnava",
			tracks: [ ]
		}
	],
	currentPageItemsCount: 2,
	pagesCount: 1,
	currentPageNum: 1,
	totalItemsCount: 2
}

```

### Get band profile

GET request to:

```
http(s)://<VPS_ADDRESS>/<PLATFORM_SLUG>/band?q=<BAND_SLUG_OR_ID>
```
* required unique parameter **BAND_SLUG_OR_ID**  

Source of data : [https://bandzone.cz/<BAND_SLUG_OR_ID>](https://bandzone.cz/thewildernesstt)

#### Example

You want to listen to "The Wilderness", this one:

```
...
	{
		"title": "The Wilderness",
		...
		"slug": "thewildernesstt",
		...
	}
...
```

request:

```
http://172.104.155.216:4000/bandzone/band?q=thewildernesstt
```

output:

```
{
	title: "The Wilderness ",
	imageUrl: "https://bzmedia.cz/band/a6/58/5319/e1/ad/8d4e/t_oOTGdVm_aXe0tC121kSp2ko_ZtrMCI.jpg",
	href: "https://bandzone.cz/thewildernesstt",
	slug: "thewildernesstt",
	genre: "punk",
	city: "Trnava",
	tracks: [
		{
			fullTitle: "Načo pojdem domov- Singel 2017 (2017)",
			title: "Načo pojdem domov",
			album: "Singel 2017 (2017)",
			playsCount: "3710",
			href: "https://bandzone.cz/track/download/697871",
			hrefHash: "28400c3aadef688166a927180e68718e",
			duration: "12:42"
		},
	...
	]
}
```

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
