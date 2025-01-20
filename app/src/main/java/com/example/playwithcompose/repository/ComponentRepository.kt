package com.example.playwithcompose.repository

import com.example.playwithcompose.models.Components
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ComponentRepository {
    fun getComponents(): List<Components> {
        val json = """
            [
              { "type": "button", "label": "Duck Me", "action": "buttonAction" },
              { "type": "text", "text": "You have been Duckified" },
              { "type": "image", "url": "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.postermywall.com%2Findex.php%2Fart%2Ftemplate%2Ff6f0cd3bbe63c9e87526e745835f34fb%2Ffunny-duck-meme-design-template&psig=AOvVaw3BsNXil8XXl4RbNxWpiy_L&ust=1737474702465000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCPCjoO7ThIsDFQAAAAAdAAAAABAJ" }
            ]
        """
        val gson = Gson()
        val listType = object : TypeToken<List<Components>>() {}.type
        return gson.fromJson(json, listType)
    }
}