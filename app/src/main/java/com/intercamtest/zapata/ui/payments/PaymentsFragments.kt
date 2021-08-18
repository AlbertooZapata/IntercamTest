package com.intercamtest.zapata.ui.payments

import android.annotation.SuppressLint
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import coil.transform.CircleCropTransformation
import com.intercamtest.zapata.R
import com.intercamtest.zapata.data.DataSource
import com.intercamtest.zapata.data.model.Payment
import com.intercamtest.zapata.databinding.FragmentPaymentsFragmentsBinding
import com.intercamtest.zapata.domain.payment.PaymentRepoImpl
import com.intercamtest.zapata.ui.payments.viewmodel.PaymentVMFactory
import com.intercamtest.zapata.ui.payments.viewmodel.PaymentViewModel
import com.intercamtest.zapata.utils.DateTimeUtil
import com.intercamtest.zapata.vo.Resource

/**
 * Created by Alberto Zapata on ago, 2021
 */
class PaymentsFragments : Fragment(), PaymentsAdapter.OnPaymentClickListener {


    private val viewModel by viewModels<PaymentViewModel> {
        PaymentVMFactory(PaymentRepoImpl(DataSource()))
    }

    private var _binding: FragmentPaymentsFragmentsBinding? = null
    private val binding get() = _binding!!
    private val classTag = "myLog [${javaClass.simpleName}]"

    companion object {
        private const val PERMISSION_GALLERY_CODE = 2
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPaymentsFragmentsBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
        setUpObserver()
        startMessage()
    }

    private fun setUpObserver() {

        viewModel.getAllPayments().observe(viewLifecycleOwner, Observer { result ->

            when (result) {
                is Resource.Loading -> {
                    Log.i(classTag, "Loading source...")
                }
                is Resource.Success -> {
                    setRecyclerView(result.data)
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), result.exception.message, Toast.LENGTH_LONG).show()
                }
            }
        })

    }

    private fun setRecyclerView(payments: List<Payment>) {
        binding.rvPayments.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvPayments.adapter = PaymentsAdapter(requireContext(), payments, this)

    }

    private fun setUI() {
        binding.userName.text = "Alberto Zapata Telles"
        binding.txtThereArePayments.text = "Tienes pagos por autorizar:"
        binding.txtCurrentDate.text = DateTimeUtil.getCurrentDate()
        binding.intercamLogo.load("https://scontent.flap2-1.fna.fbcdn.net/v/t1.6435-9/49656297_1491116987699950_1159162719153160192_n.png?_nc_cat=110&ccb=1-5&_nc_sid=973b4a&_nc_ohc=WOU-QIBePjIAX_KiOhF&_nc_ht=scontent.flap2-1.fna&oh=4ebcec8a6eec0abdef653fc82c2d4867&oe=61431FAC") {
            transformations(CircleCropTransformation())
        }
        binding.editProfilePicture.setOnClickListener {
            chooseImage()
        }
        binding.exitBtnContainer.setOnClickListener {
            exitConfirmation()
        }
        binding.supportBtnContainer.setOnClickListener {
            Toast.makeText(requireContext(), "En proceso...", Toast.LENGTH_LONG).show()
        }

        binding.btnFilter.setOnClickListener {
            findNavController().navigate(R.id.action_paymentsFragments_to_beersFragment)
        }


    }


    private fun chooseImage() {
        val pickPhoto =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickPhoto, PERMISSION_GALLERY_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.i(classTag, "resultCode: $resultCode, requestCode: $requestCode")

        if (resultCode != RESULT_CANCELED) {
            when (requestCode) {
                PERMISSION_GALLERY_CODE -> if (resultCode == RESULT_OK && data != null) {
                    val photoUri: Uri = data.data!!
                    binding.intercamLogo.load(photoUri) {
                        transformations(CircleCropTransformation())
                    }
                }
            }
        }
    }

    private fun exitConfirmation() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Aviso")
        builder.setMessage("¿Deseas salir de la aplicación?")

        builder.setPositiveButton("Aceptar") { _, _ ->
            requireActivity().finish()
        }
        builder.setNegativeButton("Cancelar") { _, _ -> }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun startMessage() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Informacion")
        builder.setMessage("Para acceder al modulo de Cervezas por favor dar click en el boton 'Filtro de busqueda'")

        builder.setPositiveButton("Aceptar") { _, _ -> }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onItemClick(payment: Payment) {

        val isAuthorized =
            if (payment.isAuthorized) "Este pago esta autorizado" else "Este pago no se encuentra autorizado"
        Toast.makeText(requireContext(), isAuthorized, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}