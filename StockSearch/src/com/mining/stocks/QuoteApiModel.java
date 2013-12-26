/**
 * 
 */
package com.mining.stocks;


	import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

	@XStreamAlias("QuoteApiModel")
	public class QuoteApiModel implements Serializable {

		private static final long serialVersionUID = 738834838444L;
		
		@XStreamAlias("Data")
		private QuoteData quoteData;
		
		/**
		 * @return the quoteData
		 */
		public QuoteData getQuoteData() {
			return quoteData;
		}

		/**
		 * @param quoteData the quoteData to set
		 */
		public void setQuoteData(QuoteData quoteData) {
			this.quoteData = quoteData;
		}

		
		/**
		 * @return the serialversionuid
		 */
		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		private Object readResolve() {
			return this;
		}
		

}
