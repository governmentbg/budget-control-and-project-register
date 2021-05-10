package bg.infosys.daeu.ws.services;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.tsp.TimeStampToken;
import org.springframework.stereotype.Service;

import bg.infosys.daeu.ws.dto.TimestampInfoDto;
import eu.europa.esig.dss.DSSUtils;
import eu.europa.esig.dss.DigestAlgorithm;
import eu.europa.esig.dss.client.http.commons.TimestampDataLoader;
import eu.europa.esig.dss.client.tsp.OnlineTSPSource;

@Service
public class TimestampService {
	private static final String TSP_SERVER_URL = "http://tsa.b-trust.org/";
	private static final DigestAlgorithm DIGEST_ALGORITHM = DigestAlgorithm.SHA256;
	private static final String ENCODING = "UTF-8";
	
	private OnlineTSPSource tspSource;
	
	public TimestampService() {
		tspSource = new OnlineTSPSource(TSP_SERVER_URL);
		
		tspSource.setDataLoader(new TimestampDataLoader());
	}
	
	public String generateToken(String data) throws Exception {
		byte[] toDigest = data.getBytes(ENCODING);
		
		final byte[] digestValue = DSSUtils.digest(DIGEST_ALGORITHM, toDigest);
		final TimeStampToken token = tspSource.getTimeStampResponse(DIGEST_ALGORITHM, digestValue);
		
		return Base64.encodeBase64String(token.getEncoded());
	}
	
	public TimestampInfoDto getTimestampInfo(String encodedToken) throws Exception {
		TimestampInfoDto result = new TimestampInfoDto();
		
		CMSSignedData data = new CMSSignedData(Base64.decodeBase64(encodedToken));
		TimeStampToken token = new TimeStampToken(data);
		
		result.setSerialNumber(token.getTimeStampInfo().getSerialNumber());
		result.setDate(token.getTimeStampInfo().getGenTime());
		
		return result;
	}
}
