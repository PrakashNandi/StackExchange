package com.candyspace.stackexchange.models

import java.util.*

/**
 * Created by Prakash Nandi on 31/01/21.
 */
class User {
    lateinit var badge_counts: BadgeCounts
    var account_id: Int = 0
    var is_employee: Boolean = false
    var last_modified_date: Long = 0
    var last_access_date: Long = 0
    var reputation_change_year: Int = 0
    var reputation_change_quarter: Int = 0
    var reputation_change_month: Int = 0
    var reputation_change_week: Int = 0
    var reputation_change_day: Int = 0
    var reputation: Int = 0
    var creation_date: Long = 0
    lateinit var user_type: String
    var user_id: Int = 0
    var accept_rate: Int = 0
    lateinit var location: String
    lateinit var website_url: String
    lateinit var link: String
    lateinit var profile_image: String
    lateinit var display_name: String
}