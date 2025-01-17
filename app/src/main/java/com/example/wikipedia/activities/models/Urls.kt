package com.example.wikipedia.activities.models

object Urls {
    private const val BaseUrl= "https://en.wikwipedia.org/w/api.php"
    fun getSearchUrl(term: String, skip: Int, take: Int): String{
        return BaseUrl + "?action=query" +
                "&formatversion=2"+
                "&generator=prefixsearch" +
                "&gpssearch=$term" +
                "&gpslimit=$take" +
                "&gpsoffset=$skip" +
                "&prop=pageimages|info" +
                "piprop=thumbnail|url" +
                "&pithumbsize=200" +
                "&pilimit=$take" +
                "&wbptterms=description" +
                "format=json" +
                "&inprop=url"
    }
    fun getRandomUrl(take: Int): String{
        return BaseUrl + "?action=query" +
                "format=json" +
                "&formatversion=2" +
                "&generator=random" +
                "&grnnamespace=0" +
                "&prop=pageimages|info" +
                "grnlimit=$take" +
                "&inprop=url" +
                "&pithumbsize=200"
    }
}