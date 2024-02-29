package com.carlosjr.eventsapp.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.carlosjr.eventsapp.R
import com.carlosjr.eventsapp.data.database.AppDatabase
import com.carlosjr.eventsapp.data.database.dao.CheckInDAO
import com.carlosjr.eventsapp.data.database.model.CheckInEvents
import com.carlosjr.eventsapp.data.model.dto.CheckInRequest
import com.carlosjr.eventsapp.databinding.ActivityDetailsBinding
import com.carlosjr.eventsapp.helper.Constants.SEND_INTENT_TEXT_TYPE
import com.carlosjr.eventsapp.helper.extensions.formatDate
import com.carlosjr.eventsapp.helper.extensions.formatDateDay
import com.carlosjr.eventsapp.helper.extensions.formatDateMonth
import com.carlosjr.eventsapp.helper.extensions.formatTime
import com.carlosjr.eventsapp.helper.extensions.hideKeyboard
import com.carlosjr.eventsapp.helper.extensions.monetaryFormat
import com.carlosjr.eventsapp.helper.extensions.parcelable
import com.carlosjr.eventsapp.helper.extensions.setVisible
import com.carlosjr.eventsapp.helper.extensions.setupPicassoImage
import com.carlosjr.eventsapp.helper.extensions.toast
import com.carlosjr.eventsapp.presentation.model.viewstate.DetailsViewState.ErrorState
import com.carlosjr.eventsapp.presentation.model.viewstate.DetailsViewState.LoadingState
import com.carlosjr.eventsapp.presentation.model.viewstate.DetailsViewState.SuccessState
import com.carlosjr.eventsapp.presentation.model.vo.EventsVO
import com.carlosjr.eventsapp.presentation.ui.HomeActivity.Companion.EVENTS_HOME_ACTIVITY_PARAMETERS
import com.carlosjr.eventsapp.presentation.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailsBinding.inflate(layoutInflater) }
    private val detailsViewModel: DetailsViewModel by viewModels()
    private lateinit var appDatabase: AppDatabase
    private lateinit var checkInDAO: CheckInDAO
    private var eventsResult: EventsVO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
        setupListeners()
    }

    override fun onStart() {
        super.onStart()
        getExtras()
        initDatabase()
        setupObserve()
    }

    private fun getExtras()  {
        intent.extras?.let {
            val eventsResult = intent.parcelable<EventsVO>(EVENTS_HOME_ACTIVITY_PARAMETERS)
            this.eventsResult = eventsResult
            setupResultView(event = eventsResult)
        }
    }

    private fun initDatabase() {
        this.appDatabase = AppDatabase.getInstance(this)
        this.checkInDAO = appDatabase.checkInDao()
        fetchCheckIn()
    }

    private fun fetchCheckIn() {
        CoroutineScope(Dispatchers.IO).launch {
            val checkIn = checkInDAO.searchAll()
            withContext(Dispatchers.Main){
                checkIn.forEach { checkIn ->
                    if (checkIn.isCheckIn && checkIn.title == eventsResult?.title) {
                        setupCheckInState()
                    }
                }
            }
        }
    }

    private fun setupObserve() = lifecycleScope.launch {
            detailsViewModel.eventsStateFlow.collect { viewState ->
                when (viewState) {
                    is LoadingState -> {
                        binding.customLoading.loadingContainer.setVisible(show = true)
                    }
                    is SuccessState -> {
                        setupCheckInState()
                        checkInDAO.insert(CheckInEvents(isCheckIn = true, title = eventsResult?.title ))
                        binding.customLoading.loadingContainer.setVisible(show = false)
                        binding.customDialog.setAnimationDialog(animation = R.raw.loading_success, isShow = true)
                    }
                    is ErrorState -> {
                        binding.customLoading.loadingContainer.setVisible(show = false)
                        binding.customDialog.setAnimationDialog(animation = R.raw.loading_error, isShow = true)
                    }
                }
            }
    }

    private fun setupCheckInState() = with(binding) {
        includeEventDetail.imageStateButton.setImageResource(R.drawable.ic_heart_circle_outline)
        toast(getString(R.string.check_in_done))
    }

    private fun setupViews() = with(binding) {
        includeActionBar.actionBarTitle.text = getString(R.string.details_event_title)
    }

    private fun setupListeners() = with(binding) {
        includeActionBar.primaryActionButton.setOnClickListener { finish() }
        includeActionBar.secondaryActionButton.setOnClickListener { shareEvent() }
        floatingActionButton.setOnClickListener {
            customDialog.setVisible(show = true)
        }
        customDialog.setupListeners(
            onBtnConfirmClick = {
                when {
                    customDialog.getInputName().isEmpty() -> { toast(getString(R.string.enter_valid_name)) }
                    customDialog.getInputEmail().isEmpty() -> { toast(getString(R.string.enter_valid_email))}
                    else -> {
                        detailsViewModel.sendCheckIn(
                            CheckInRequest(
                                eventId = eventsResult?.id ?: ZERO_VALUE,
                                name = customDialog.getInputName(),
                                email = customDialog.getInputEmail(),
                            )
                        )
                        hideKeyboard(view = binding.root)
                    }
                }
            },
            onBtnCancelClick = {
                customDialog.setVisible(show = false)
                hideKeyboard(view = binding.root)
            },
            onBtnCloseClick = {
                customDialog.setVisible(show = false)
                customDialog.setAnimationDialog(isShow = false)
            }
        )
    }

    private fun setupResultView(event: EventsVO?) = with(binding) {
        val eventDate = event?.date.toString().dropLast(LENGTH_THREE).toLong()
        val treatedDate = getDateTime(eventDate)

        setupEventDetail(event, eventDate, treatedDate)
        setupCustomDialog(event)
        setupPicassoImage(image = event?.image, error = R.drawable.tech_background, view = includeEventDetail.imageEvent)
    }

    private fun setupEventDetail(event: EventsVO?, eventDate: Long, treatedDate: String) = with(binding) {
        includeEventDetail.apply {
            textViewEventTitle.text = event?.title
            textViewEventDescription.text = event?.description
            textViewEventDate.text = treatedDate
            textViewEventDay.text = formatDateDay(eventDate)
            textViewEventMonth.text = formatDateMonth(eventDate)
            textViewEventPrice.text = event?.price?.monetaryFormat()
        }
    }

    private fun setupCustomDialog(event: EventsVO?) = with(binding) {
        customDialog.apply {
            setDialogTitle(event?.title)
            setDialogBtnConfirm(getString(R.string.custom_dialog_check_in))
            setDialogBtnCancel(getString(R.string.custom_dialog_close))
        }

    }

    private fun shareEvent() {
        val sendMessage = getString(R.string.details_send_event_message, eventsResult?.title, eventsResult?.description)
        val sendIntent = Intent()
        sendIntent.setAction(Intent.ACTION_SEND)
        sendIntent.putExtra(Intent.EXTRA_TEXT, sendMessage)
        sendIntent.setType(SEND_INTENT_TEXT_TYPE)
        startActivity(sendIntent)
    }

    private fun getDateTime(timestamp: Long): String {
        val date = formatDate(timestamp = timestamp)
        val time = formatTime(timestamp = timestamp)
        return getString(R.string.details_event_date, date, time)
    }

    companion object {
        private const val ZERO_VALUE = "0"
        private const val LENGTH_THREE = 3
    }
}