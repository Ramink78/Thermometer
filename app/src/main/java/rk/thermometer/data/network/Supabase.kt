package rk.thermometer.data.network

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime

val supabase = createSupabaseClient(
    supabaseUrl = "https://gfwmommizghbsrfuzsjn.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imdmd21vbW1pemdoYnNyZnV6c2puIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTIwNjc0NTAsImV4cCI6MjAyNzY0MzQ1MH0.YCDxOZyKv54qQFl4xJAOtuQqgoH56v1YMt86MBlKUuA"
) {
    install(Postgrest)
    install(Realtime)
}