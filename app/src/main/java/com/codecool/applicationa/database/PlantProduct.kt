package com.codecool.applicationa.database

class PlantProduct(val productName : String, val productPrice : Int, val productDescription : String, val imageLink : String) {
    companion object{
        val PRODUCT_LIST = listOf(
            PlantProduct("Product 1", 12, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lobortis elit convallis sit neque, purus.", "https://i.imgur.com/ivw8qGV.png"),
            PlantProduct("Product 2", 13, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lobortis elit convallis sit neque, purus.", "https://i.imgur.com/ivw8qGV.png"),
            PlantProduct("Product 3", 14, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lobortis elit convallis sit neque, purus.", "https://i.imgur.com/ivw8qGV.png"),
            PlantProduct("Product 4", 15, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lobortis elit convallis sit neque, purus.", "https://i.imgur.com/ivw8qGV.png"),
            PlantProduct("Product 5", 16, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lobortis elit convallis sit neque, purus.", "https://i.imgur.com/ivw8qGV.png"),
            PlantProduct("Product 6", 17, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lobortis elit convallis sit neque, purus.", "https://i.imgur.com/ivw8qGV.png"),
            PlantProduct("Product 7", 18, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lobortis elit convallis sit neque, purus.", "https://i.imgur.com/ivw8qGV.png"),
            PlantProduct("Product 8", 19, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lobortis elit convallis sit neque, purus.", "https://i.imgur.com/ivw8qGV.png"),
            PlantProduct("Product 9", 20, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lobortis elit convallis sit neque, purus.", "https://i.imgur.com/ivw8qGV.png"),
            PlantProduct("Product 10", 21, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lobortis elit convallis sit neque, purus.", "https://i.imgur.com/ivw8qGV.png"),
            PlantProduct("Product 11", 22, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lobortis elit convallis sit neque, purus.", "https://i.imgur.com/ivw8qGV.png"),
            PlantProduct("Product 12", 10, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lobortis elit convallis sit neque, purus.", "https://i.imgur.com/ivw8qGV.png"),
            PlantProduct("Product 13", 5, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lobortis elit convallis sit neque, purus.", "https://i.imgur.com/ivw8qGV.png"),
            PlantProduct("Product 14", 2, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lobortis elit convallis sit neque, purus.", "https://i.imgur.com/ivw8qGV.png"),
            PlantProduct("Product 15", 100, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lobortis elit convallis sit neque, purus.", "https://i.imgur.com/ivw8qGV.png")
        )
    }
}