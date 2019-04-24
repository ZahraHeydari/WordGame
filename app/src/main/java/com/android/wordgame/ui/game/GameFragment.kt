package com.android.wordgame.ui.game

import android.animation.Animator
import android.animation.ObjectAnimator
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import com.android.wordgame.R
import com.android.wordgame.ui.main.OnMainActivityCallback
import com.android.wordgame.databinding.FragmentGameBinding
import dagger.android.support.DaggerFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.ClassCastException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GameFragment : DaggerFragment() {

    private val TAG = GameFragment::class.java.simpleName
    private val ANSWER_TIME = 10000L
    private lateinit var binding: FragmentGameBinding
    private var mCallback: OnMainActivityCallback? = null
    private var mCountDownTimer: CountDownTimer? = null
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: GameViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(GameViewModel::class.java)
    }
    lateinit var animation: Animator
    private val compositeDisposable = CompositeDisposable()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnMainActivityCallback) {
            mCallback = context
        } else throw ClassCastException("${context.toString()} must implement OnMainActivityCallback!")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        binding.gameViewModel = viewModel
        binding.executePendingBindings()

        return binding.root
    }

    private fun showQuestion() {
        enableButtons()
        compositeDisposable.add(
            Observable.timer(200, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewModel.getRandomQuestion()
                    resetView()
                    startTimerDown()
                    makeAnimation()
                }, { t: Throwable? ->
                    Log.e(TAG, "Throwable " + t?.message)
                })
        )

    }

    private fun resetView() {
        if (!isAdded) return
        binding.gameAnswerTextView.translationY = 0f
        binding.gameAnswerTextView.setTextColor(ContextCompat.getColor(context!!, R.color.black))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showQuestion()

        with(viewModel) {
            this.isGameEnded.observe(this@GameFragment, Observer {
                if (it!!) {
                    disableButtons()
                    showEndGameDialog()
                    compositeDisposable.add(
                        Observable.timer(100, TimeUnit.MILLISECONDS)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                activity?.onBackPressed()
                            }, { t: Throwable? ->
                                Log.e(TAG, "Throwable " + t?.message)
                            })
                    )
                }
            })
        }

        viewModel.point.observe(this, Observer {
            binding.gamePointTextView.text = getString(R.string.point, it)
        })

        viewModel.question.observe(this, Observer {
            binding.gameQuestionTextView.text = it?.native

        })

        viewModel.answer.observe(this, Observer {
            binding.gameAnswerTextView.text = it?.translation
        })

        binding.gameCorrectImageView.setOnClickListener {
            checkAnswer(true)
        }

        binding.gameIncorrectImageView.setOnClickListener {
            checkAnswer(false)
        }

        viewModel.isAnswerCorrect.observe(this, Observer {
            if (it!!) {
                binding.gameAnswerTextView.setTextColor(ContextCompat.getColor(context!!, R.color.green))
            } else {
                binding.gameAnswerTextView.setTextColor(ContextCompat.getColor(context!!, R.color.red))
            }
        })
    }

    private fun showEndGameDialog() {
        context?.let { ctx ->
            AlertDialog.Builder(ctx)
                .setTitle(R.string.end_game_title)
                .setMessage(getString(R.string.end_game_message, viewModel.point.value))
                .setCancelable(false)
                .setPositiveButton(R.string.ok) { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }
    }

    private fun checkAnswer(isCorrect: Boolean) {
        disableButtons()
        viewModel.checkUserAnswer(isCorrect)
        compositeDisposable.add(
            Observable.timer(100, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    showQuestion()
                }, { t: Throwable? ->
                    Log.e(TAG, "Throwable " + t?.message)
                })
        )
    }

    private fun startTimerDown() {
        mCountDownTimer?.cancel()
        mCountDownTimer = setCountdown()
        mCountDownTimer?.start()
    }


    private fun makeAnimation() {
        val toFloat = (binding.gameAnswerTextView.parent as ViewGroup).height.toFloat()
        Log.i("GameFragment", toFloat.toString())
        animation = ObjectAnimator.ofFloat(
            binding.gameAnswerTextView, "translationY",
            (binding.gameAnswerTextView.parent as ViewGroup).height.toFloat()
        )
        animation.interpolator = LinearInterpolator()
        animation.duration = ANSWER_TIME
        animation.start()
        animation.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                showQuestion()
            }

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationRepeat(animation: Animator) {}
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (compositeDisposable.isDisposed) compositeDisposable.dispose()
    }

    private fun disableButtons() {
        binding.gameCorrectImageView.isEnabled = false
        binding.gameIncorrectImageView.isEnabled = false
    }

    private fun enableButtons() {
        binding.gameCorrectImageView.isEnabled = true
        binding.gameIncorrectImageView.isEnabled = true
    }

    private fun setCountdown(): CountDownTimer {
        return object : CountDownTimer(ANSWER_TIME, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.gameTimerTextView.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
            }
        }
    }

    companion object {

        val FRAGMENT_NAME = GameFragment::class.java.name

        fun newInstance() =
            GameFragment().apply {
                arguments = Bundle().apply {
                    //nothing yet
                }
            }
    }


}