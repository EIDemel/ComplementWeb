package edu.spring.models

class Items(var nom: String) {

    var evaluation:Int = 0


    override fun equals(obj: Any?): Boolean {
        return if (obj is Items) {
            nom == obj.nom
        } else false
    }
}

