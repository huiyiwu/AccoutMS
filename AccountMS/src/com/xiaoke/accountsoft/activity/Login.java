package com.xiaoke.accountsoft.activity;

import com.xiaoke.accountsoft.dao.PwdDAO;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	EditText txtlogin;// ����EditText����
	Button btnlogin,btnRes;// ��������Button����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);// ���ò����ļ�

		txtlogin = (EditText) findViewById(R.id.txtLogin);// ��ȡ�����ı���
		btnlogin = (Button) findViewById(R.id.btnLogin);// ��ȡ��¼��ť
		btnRes = (Button) findViewById(R.id.btnRes);

		btnlogin.setOnClickListener(new OnClickListener() {// Ϊ��¼��ť���ü����¼�
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Login.this, MainActivity.class);// ����Intent����
				PwdDAO pwdDAO = new PwdDAO(Login.this);// ����PwdDAO����
				// �ж��Ƿ������뼰�Ƿ�����������
				if ((pwdDAO.getCount() == 0 || pwdDAO.find().getPassword()
						.isEmpty())
						&& txtlogin.getText().toString().isEmpty()) {
					startActivity(intent);// ������Activity
				} else {
					// �ж�����������Ƿ������ݿ��е�����һ��
					if (pwdDAO.find().getPassword()
							.equals(txtlogin.getText().toString())) {
						startActivity(intent);// ������Activity
					} else {
						// ������Ϣ��ʾ
						Toast.makeText(Login.this, "��������ȷ�����룡",
								Toast.LENGTH_SHORT).show();
					}
				}
				txtlogin.setText("");// ��������ı���
			}
		});

	
		btnRes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Login.this,Sysset.class);
				startActivityForResult(intent, 0X001);
			}
		});
	
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode==0X001&&resultCode==0X001) {
			txtlogin.setText(data.getStringExtra("pwd"));
		}
	}
}
