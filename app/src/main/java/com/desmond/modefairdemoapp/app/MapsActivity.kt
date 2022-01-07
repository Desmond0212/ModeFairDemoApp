package com.desmond.modefairdemoapp.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.viewpager.widget.ViewPager
import com.desmond.modefairdemoapp.R
import com.desmond.modefairdemoapp.adapter.CardStackAdapter
import com.desmond.modefairdemoapp.adapter.OrderItemListAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.desmond.modefairdemoapp.databinding.ActivityMapsBinding
import com.desmond.modefairdemoapp.model.OrderItemModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var items: MutableList<String> = mutableListOf()
    private var cardStackAdapter: CardStackAdapter? = null
    private var orderList: MutableList<OrderItemModel> = mutableListOf()
    private var orderItemListAdapter: OrderItemListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val location1 = LatLng(3.14924, 101.69570)
        val location2 = LatLng(3.14685, 101.69463)

        mMap.addMarker(MarkerOptions().position(location1).title("Store Location").icon(
            BitmapDescriptorFactory.fromResource(R.drawable.icon_user)))
        mMap.addMarker(MarkerOptions().position(location2).title("Destination Location").icon(
            BitmapDescriptorFactory.fromResource(R.drawable.icon_shopping_cart)))
        mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location1, 16F), 2000, null)

        val cardStackViewPager: ViewPager = findViewById(R.id.cardStackViewPager)
        val recyclerViewOrderItemList: RecyclerView = findViewById(R.id.recyclerViewOrderItemList)
        val lblDeliveryDescription: TextView = findViewById(R.id.lblDeliveryDescription)

        val deliveryDescSpannableString = SpannableString(resources.getString(R.string.map_activity_delivery_description))
        deliveryDescSpannableString.setSpan(ForegroundColorSpan(applicationContext.getColor(R.color.lightGreen)), 12, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        lblDeliveryDescription.text = deliveryDescSpannableString

        items.add("1")
        items.add("2")
        items.add("3")

        cardStackAdapter = CardStackAdapter(applicationContext, items)
        cardStackViewPager.offscreenPageLimit = 3
        cardStackViewPager.setPageTransformer(true) { page, position ->
            if (position >= 0) {
                page.scaleX = 0.9f - 0.2f * position
                page.scaleY = 0.9f
                page.translationX = -page.width * position
                page.translationY = 15 * position
            }
        }

        cardStackViewPager.adapter = cardStackAdapter

        orderList.add(OrderItemModel("1x", "Novel Large Oranges 200g", "AED 4.25"))
        orderList.add(OrderItemModel("1x", "Almarai Full Fat Fresh Milk 2L", "AED 10.50"))
        orderList.add(OrderItemModel("1x", "Tea Tree Cool Shampoo", "AED 110.20"))
        orderList.add(OrderItemModel("1x", "Hass Avocado Exotic Fruit", "AED 10.75"))

        recyclerViewOrderItemList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        (recyclerViewOrderItemList.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        orderItemListAdapter = OrderItemListAdapter(applicationContext, orderList)
        recyclerViewOrderItemList.adapter = orderItemListAdapter
    }
}