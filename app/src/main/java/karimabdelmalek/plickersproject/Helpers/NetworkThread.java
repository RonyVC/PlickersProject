package karimabdelmalek.plickersproject.Helpers;

import android.os.Handler;

/**
 * threading mechanism
 * 
 * @author amr.adly
 * 
 */
public class NetworkThread extends Thread {

	/**
	 * the web call back interface
	 */
	private WebDataCallBack _iCallBack;
	private Exception ex;
	private Object result;

	public NetworkThread(WebDataCallBack iCallBack) {
		_iCallBack = iCallBack;
		mHandler = new Handler();
	}

	/**
	 * Need handler for call backs to the UI thread
	 */
	private final Handler mHandler;

	/**
	 * Runnable for posting
	 */
	private final Runnable mUpdateResults = new Runnable() {
		public void run() {
			if (ex == null)
				_iCallBack.resumeUi(result);
			else
				_iCallBack.handleException(ex);
		}
	};

	@Override
	public void run() {
		super.run();
		try {
			result = _iCallBack.callService();
		} catch (Exception e) {
			
			ex = e;
			e.printStackTrace();
			
		}
		mHandler.post(mUpdateResults);
	}

}
