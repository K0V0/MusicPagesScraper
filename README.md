# MusicPagesScraper

**MusicPagesScraper** is rewrite of [BandzoneScraper](https://github.com/K0V0/BandzoneScraper) into Java & Spring Boot with intention 
to rebuild it as universal and modular easy extensible API for scraping multiple pages into single JSON data format.


## Functions

Current version supporting querying list of bands and querying band profile with tracks.  
Virtual server runs on [http://172.104.155.216:4000/](http://172.104.155.216:4000/) or [http://kovo.space:4000/](http://kovo.space:4000/)  

### Supported platforms list

| Platform (site) | Slug |
| --------------- | ---- |
| Bandzone.cz | bandzone | 


### Search bands by name, query bands list (paginated, full search)  

GET request to:

```
http(s)://<VPS_ADDRESS>/<PLATFORM_SLUG>/bands?q=<SEARCHED_BAND>&p=<PAGE_NUMBER>
```
* optional parameter **SEARCHED_BAND**  
* optional parameter **PAGE_NUMBER**  

Source of data: [https://bandzone.cz/kapely.html?q=<SEARCHED_BAND>&p=<PAGE_NUMBER>](https://bandzone.cz/kapely.html)

#### Example

request:

```
http://172.104.155.216:4000/bandzone/bands?q=wilderness
```

output:

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





