package com.example.roomdemo.db

import com.example.roomdemo.db.Subscriber
import com.example.roomdemo.db.SubscriberDAO

class SubscriberRepository(private val dao: SubscriberDAO) {
	
	val subscribers = dao.getAllSubscribers()
	
	suspend fun insert(subscriber: Subscriber): Long {
		return dao.insertSubscriber(subscriber)
	}
	
	suspend fun update(subscriber: Subscriber): Int {
		return dao.updateSubscriber(subscriber)
	}
	
	suspend fun delete(subscriber: Subscriber): Int {
		return dao.deleteSubscriber(subscriber)
	}
	
	suspend fun deleteAll(): Int {
		return dao.deleteAll()
	}
}
