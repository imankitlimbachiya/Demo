package com.app.task.api

import com.app.task.utils.NetworkResult
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                when {
                    response.code() == 401 -> {
                        return error("${response.code()} ${response.message()}")
                    }
                    response.code() == 403 -> {
                        response.errorBody()!!.let {
                            return try {
                                assert(response.errorBody() != null)
                                val resBody = response.errorBody()!!.string()
                                val jsonObject = JSONObject(resBody)
                                val message = jsonObject.getString("status")
                                NetworkResult.Error(message)
                            } catch (e: IOException) {
                                e.printStackTrace()
                                NetworkResult.Error(e.localizedMessage!!)
                            } catch (e: JSONException) {
                                e.printStackTrace()
                                NetworkResult.Error(e.localizedMessage!!)
                            }
                        }
                    }
                    else -> {
                        val body = response.body()
                        body?.let {
                            return NetworkResult.Success(body)
                        }
                    }
                }
            } else {
                when {
                    response.code() == 401 -> {
                        return error("${response.code()} ${response.message()}")
                    }
                    response.code() == 403 -> {
                        response.errorBody()!!.let {
                            return try {
                                assert(response.errorBody() != null)
                                val resBody = response.errorBody()!!.string()
                                val jsonObject = JSONObject(resBody)
                                val message = jsonObject.getString("status")
                                NetworkResult.Error(message)
                            } catch (e: IOException) {
                                e.printStackTrace()
                                NetworkResult.Error(e.localizedMessage!!)
                            } catch (e: JSONException) {
                                e.printStackTrace()
                                NetworkResult.Error(e.localizedMessage!!)
                            }
                        }
                    }
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): NetworkResult<T> {
        return NetworkResult.Error("Api call failed $errorMessage")
    }
}