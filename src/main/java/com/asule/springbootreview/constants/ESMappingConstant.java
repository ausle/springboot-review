package com.asule.springbootreview.constants;

public interface ESMappingConstant {


    String MAPPING_TEMPLATE_HOTEL="{" +
            "  \"mappings\": {" +
            "    \"properties\": {" +
            "      \"all\": {" +
            "        \"type\": \"text\"," +
            "        \"analyzer\": \"ik_max_word\"" +
            "      }," +
            "      \"id\": {" +
            "        \"type\": \"keyword\"" +
            "      }," +
            "      \"name\": {" +
            "        \"type\": \"text\"," +
            "        \"analyzer\": \"ik_max_word\"," +
            "        \"copy_to\": \"all\"" +
            "      }," +
            "      \"address\": {" +
            "        \"type\": \"keyword\"," +
            "        \"index\": false" +
            "      }," +
            "      \"price\": {" +
            "        \"type\": \"integer\"" +
            "      }," +
            "      \"score\": {" +
            "        \"type\": \"integer\"" +
            "      }," +
            "      \"brand\": {" +
            "        \"type\": \"keyword\"," +
            "        \"copy_to\": \"all\"" +
            "      }," +
            "      \"city\": {" +
            "        \"type\": \"keyword\"" +
            "      }," +
            "      \"star_name\": {" +
            "        \"type\": \"keyword\"" +
            "      }," +
            "      \"business\": {" +
            "        \"type\": \"keyword\"," +
            "        \"copy_to\": \"all\"" +
            "      }," +
            "      \"location\": {" +
            "        \"type\": \"geo_point\"" +
            "      }," +
            "      \"pic\": {" +
            "        \"type\": \"keyword\"," +
            "        \"index\": false" +
            "      }" +
            "    }" +
            "  }" +
            "}";



}
