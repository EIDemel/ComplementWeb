package edu.spring.td2.services
class UISuppression {

    class Suppression(var title:String, var message: String, var type:String, var id:Int)

    companion object {
        fun suppression(title: String, message: String, type: String, id:Int) =
            Suppression(title, message, type, id)

    }

}