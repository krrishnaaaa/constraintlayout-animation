package com.pcsalt.example.constraintanimation

import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionManager
import kotlinx.android.synthetic.main.activity_main.*




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        initBackground()

        initConstraint()

    }

    private fun initBackground() {
    }

    private fun initConstraint() {
        val sqConstraint = ConstraintSet()
        sqConstraint.clone(cl)

        val cirConstraint = ConstraintSet()
        cirConstraint.clone(this, R.layout.activity_main_alt)

        var set = false
        btn.setOnClickListener {
            val changeBounds = ChangeBounds()
            changeBounds.addListener(object : Transition.TransitionListener {
                override fun onTransitionStart(transition: Transition) {
                    iv.setIsCircle(set)
                    if (set) {
                        iv.borderWidth = 20
                        iv.borderColor = ContextCompat.getColor(this@MainActivity, R.color.colorPrimary)
                    }
                    iv.invalidate()
                }

                override fun onTransitionEnd(transition: Transition) {
                    btn.text = if (set) getString(R.string.to_rounded_corner) else getString(R.string.to_circle)
                }

                override fun onTransitionCancel(transition: Transition) {}

                override fun onTransitionPause(transition: Transition) {}

                override fun onTransitionResume(transition: Transition) {}
            })

            TransitionManager.go(Scene(cl), changeBounds)
            val constraint = if (set) sqConstraint else cirConstraint
            constraint.applyTo(cl)
            set = !set
        }
    }
}
